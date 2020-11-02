package com.springboot.archetype.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author WXH
 * @date 2020/11/2 14:00
 */
@Service
public class ActiveMQQueueConsumer {

    private Logger logger = LoggerFactory.getLogger(ActiveMQQueueConsumer.class);

    @JmsListener(destination = ActiveMQConfig.JMS_QUEUE,containerFactory = ActiveMQConfig.JMS_LISTENER_CONTAINER_FACTORY_QUEUE)

    public void onQueueMessage(String msg) {
        logger.info("接收到一条队列消息：{}",msg);
    }
}