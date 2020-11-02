package com.springboot.archetype.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author WXH
 * @date 2020/11/2 14:01
 */
@Service
public class ActiveMQTopicPublisher {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMQTopicPublisher.class);

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendTopicMessage(Destination destination, String message) {
        logger.info("发布者发布一条话题：{}。", message);
        this.jmsTemplate.convertAndSend(destination,message);
    }
}