package com.dili.account.listener;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dili.account.entity.SerialRecordDo;
import com.dili.account.service.IAccountManageService;
import com.dili.account.service.ISerialRecordService;
import com.dili.customer.sdk.constants.MqConstant;
import com.dili.customer.sdk.domain.Customer;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @description： 
 *          客户信息变动时更新账户冗余信息
 * @author ：WangBo
 * @time ：2020年8月14日上午10:36:38
 */
@ConditionalOnExpression("'${customer_mq.enable}'=='true'")
@Component
public class CustomerMQListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerMQListener.class);
    public static final String EXCHANGE_CUSTOMER_CHANGE = "dili.customer.fanoutExchange";
    @Autowired
    IAccountManageService accountManageService;
    /**
     * 客户信息修改后，更新账户冗余信息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "customer.info", autoDelete = "false"),
            exchange = @Exchange(value = MqConstant.CUSTOMER_MQ_FANOUT_EXCHANGE, type = ExchangeTypes.FANOUT)
    ))
    public void processCustomerInfo(Channel channel, Message message) {
        try {
            String data = new String(message.getBody(), "UTF-8");
            if (!StrUtil.isBlank(data)) {
            	Customer customer = JSONObject.parseObject(data,Customer.class);
            	accountManageService.updateCustomerInfo(customer);
            }
        } catch (Exception e) {
            LOGGER.error("客户信息修改失败", message, e);
        }
    }
}
