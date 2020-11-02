package com.springboot.archetype.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author WXH
 * @date 2020/11/2 13:58
 */
@Service
public class ActiveMQQueueProducer {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMQQueueProducer.class);

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendQueueMessage(Destination destination, String message) {
        logger.info("生产者生成一条队列消息：{}。", message);
        this.jmsTemplate.convertAndSend(destination,message);
    }

}