package com.dili.account.listener;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dili.account.entity.SerialRecordDo;
import com.dili.account.service.ISerialRecordService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * rabbit mq 消息监听器
 */
@Component
public class RabbitMQListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQListener.class);
    //账户流水
    public static final String EXCHANGE_ACCOUNT_SERIAL = "exchange_account_serial";
    public static final String QUEUE_ACCOUNT_SERIAL = "queue_account_serial";
    public static final String ROUTING_ACCOUNT_SERIAL = "routing_account_serial";

    @Resource
    private ISerialRecordService serialRecordService;
    /**
     * 处理账户流水消息数据
     * @param channel
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE_ACCOUNT_SERIAL, autoDelete = "false"),
            exchange = @Exchange(value = EXCHANGE_ACCOUNT_SERIAL, type = ExchangeTypes.DIRECT)
    ))
    public void processAccountSerial(Channel channel, Message message) {
        try {
            String data = new String(message.getBody(), "UTF-8");
            if (!StrUtil.isBlank(data)) {
                List<SerialRecordDo> serialRecordList = JSON.parseArray(data, SerialRecordDo.class);
                serialRecordService.batchSave(serialRecordList);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            LOGGER.error("转换对象: {} 出错 {}", message, e);
            // redelivered = true, 表明该消息是重复处理消息
            Boolean redelivered = message.getMessageProperties().getRedelivered();
            try {
                if (redelivered) {
                    /**
                     * 1. 对于重复处理的队列消息做补偿机制处理
                     * 2. 从队列中移除该消息，防止队列阻塞
                     */
                    // 消息已重复处理失败, 扔掉消息
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                    LOGGER.error("消息 {} 重新处理失败，扔掉消息", message);
                } else {
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                }
            } catch (IOException ex) {
                LOGGER.error("消息 {} 重放回队列失败 {}", message, ex);
            }
        }
    }
}
