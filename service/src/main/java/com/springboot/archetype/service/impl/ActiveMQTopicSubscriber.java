package com.springboot.archetype.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author WXH
 * @date 2020/11/2 14:02
 */
@Service
public class ActiveMQTopicSubscriber {

    private Logger logger = LoggerFactory.getLogger(ActiveMQQueueConsumer.class);

    @JmsListener(destination = ActiveMQConfig.JMS_TOPIC,containerFactory = ActiveMQConfig.JMS_LISTENER_CONTAINER_FACTORY_TOPIC)
    public void onTopicMessage(String msg) {
        logger.info("订阅者收到一条主题：{}",msg);
    }

}