package net.roxia.scheduler.handler;

import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.adapter.enums.OperateEnum;
import net.roxia.scheduler.message.Header;
import net.roxia.scheduler.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName MessageHandlerProcessor
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 15:10
 **/
public class MessageHandlerProcessor {
    private static final Logger log = LoggerFactory.getLogger(MessageHandlerProcessor.class);

    public static String assignmentMsg(Message message) {
        Header header = message.getHeader();
        String type = header.getType();
        OperateEnum operateEnum = OperateEnum.getByCode(type);
        if (operateEnum == null) {
            log.warn("unknown message type! type={}", type);
        }
        OperateAdapter.getOperateAdapter(operateEnum).handle(message);
        return null;
    }
}