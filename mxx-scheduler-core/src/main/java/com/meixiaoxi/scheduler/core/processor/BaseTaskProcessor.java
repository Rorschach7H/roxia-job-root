package com.meixiaoxi.scheduler.core.processor;

import com.alibaba.fastjson.JSON;
import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.common.DateUtil;
import com.meixiaoxi.scheduler.common.TaskConstUtil;
import com.meixiaoxi.scheduler.core.handler.TaskExecuteHandler;
import com.meixiaoxi.scheduler.core.task.domain.ExecuteState;
import com.meixiaoxi.scheduler.core.task.domain.RunExecutingTask;
import com.meixiaoxi.scheduler.store.cache.RedissonFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Title 任务运行处理器
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/7/9 11:24
 * @Version V1.0
 */
public abstract class BaseTaskProcessor implements TaskProcessor {

    private static Logger log = LoggerFactory.getLogger(BaseTaskProcessor.class);

    private static String postfix = "_nmslwsnd";

    private final RedissonClient redissonClient;

    public BaseTaskProcessor(SchedulerConfig config) {
        redissonClient = RedissonFactory.getRedissonClient(config);
    }

    @Override
    public boolean addTask(RunExecutingTask taskInfo) {
        try {
            if (check(taskInfo)) {
                long timestamp = DateUtil.dateToTimestramp(taskInfo.getExecuteTime(), TaskConstUtil.TIME_YYYYMMDDHHMMSS);
                RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet(taskInfo.getGroupKey());
                log.info("添加任务到[{}]任务组, score={}", taskInfo.getGroupKey(), timestamp);
                scoredSortedSet.addAsync(timestamp, taskInfo.toString());
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("添加任务出现异常！taskInfo=" + JSON.toJSONString(taskInfo), e);
            return false;
        }
    }

    @Override
    public boolean removeTask(Long objectId) {
        return false;
    }

    /**
     * 执行任务
     *
     * @param taskGroup
     * @param handler
     */
    @Override
    public List<RunExecutingTask> executeTask(String taskGroup, TaskExecuteHandler handler) {
        try {
            List<RunExecutingTask> taskInfoList = popTaskList(taskGroup);
            for (RunExecutingTask taskInfo : taskInfoList) {
                if (handler.executeTask(taskInfo)) {
                    taskInfo.setExecuteState((byte) 1);
                    continue;
                }
                //执行失败
                log.warn("任务执行失败！taskInfo={}", JSON.toJSONString(taskInfo));
                //失败次数加1
                taskInfo.setFailures(taskInfo.getFailures() + 1);
                if (taskInfo.getFailures().intValue() == taskInfo.getMaxFailures().intValue()) {
                    log.warn("该任务失败次数已经达到了最大失败次数(3次)！无法继续尝试运行，请检查任务对应的业务逻辑是否正确，taskInfo={}", JSON.toJSONString(taskInfo));
                    taskInfo.setExecuteState((byte) ExecuteState.FAIL.getCode());
                    continue;
                }
                //下一次执行时间改为当前时间的10秒后
                taskInfo.setExecuteTime(DateUtil.dateToString(DateUtil.changeDate(new Date(),
                        DateUtil.TimeUnit.second, 10), TaskConstUtil.TIME_YYYYMMDDHHMMSS));
                //重新放回到任务等待队列中
                addTask(taskInfo);
                log.info("重新添加该任务到[{}]任务组, taskInfo={}", taskInfo.getGroupKey(), taskInfo);
            }
            return taskInfoList;
        } catch (Exception e) {
            log.error("执行任务出现异常！", e);
        }
        return null;
    }

    private List<RunExecutingTask> popTaskList(String taskGroup) {
        long timestamp = new Date().getTime() / 1000;
        List<RunExecutingTask> taskInfoList = new ArrayList<>();
        while (true) {
            try {
                Set<RunExecutingTask> taskInfoSet = getAndRemoveTaskByScore(taskGroup, timestamp);
                if (CollectionUtils.isEmpty(taskInfoSet)) {
                    break;
                }
                log.info("从任务组[{}],得到score<=[{}]的任务: {}", taskGroup, timestamp, JSON.toJSONString(taskInfoSet));
                taskInfoList.addAll(taskInfoSet);
            } catch (Exception e) {
                log.warn("获取任务出现异常:", e);
                break;
            }
        }

        return taskInfoList;
    }

    /**
     * 获取并移除任务
     *
     * @param taskGroup
     * @param timestamp
     * @return
     */
    private Set<RunExecutingTask> getAndRemoveTaskByScore(String taskGroup, long timestamp) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(taskGroup + postfix);
        Collection<ScoredEntry<String>> taskInfoList =
                sortedSet.entryRange(0, false, timestamp, true);
        if (CollectionUtils.isNotEmpty(taskInfoList)) {
            //如果获取到最要执行的任务，那么进行从任务组出移除
            sortedSet.removeRangeByScore(0, false, timestamp, true);
            return taskInfoList.stream().map(entry -> JSON.parseObject(entry.getValue(), RunExecutingTask.class)).collect(Collectors.toSet());
        }
        return null;
    }

    private boolean check(RunExecutingTask taskInfo) {
        if (StringUtils.isBlank(taskInfo.getExecuteTime())) {
            log.warn("taskInfo无执行时间！");
            return false;
        }
        if (StringUtils.isBlank(taskInfo.getGroupKey())) {
            log.warn("taskInfo无任务组名！");
            return false;
        }
        return true;
    }
}
