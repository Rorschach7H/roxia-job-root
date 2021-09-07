package net.roxia.scheduler.persistence.mapper;

import net.roxia.scheduler.core.task.domain.TaskQuery;
import net.roxia.scheduler.persistence.entity.AbstractEntity;

import java.util.List;

/**
 * @Title 任务数据库操作接口
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/8/28 10:05
 * @Version V1.0
 */
public interface Mapper<T extends AbstractEntity> {

    boolean insertSelective(T task);

    boolean insert(T task);

    boolean insertBatch(List<T> task);

    boolean update(T task);

    boolean updateBatch(List<T> tasks);

    boolean delete(Long id);

    T select(Long id);

    List<T> select(TaskQuery query);
}
