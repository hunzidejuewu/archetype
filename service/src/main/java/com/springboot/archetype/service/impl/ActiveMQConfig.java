package com.springboot.archetype.service.impl;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * @author WXH
 * @date 2020/11/2 13:55
 */
@Configuration
public class ActiveMQConfig {

    public final static String JMS_LISTENER_CONTAINER_FACTORY_QUEUE = "jmsListenerContainerFactoryQueue";
    public final static String JMS_LISTENER_CONTAINER_FACTORY_TOPIC = "jmsListenerContainerFactoryTopic";
    public final static String JMS_QUEUE = "jmsQueue";
    public final static String JMS_TOPIC = "jmsTopic";

    @Bean(JMS_LISTENER_CONTAINER_FACTORY_TOPIC)
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;
    }


    @Bean(JMS_LISTENER_CONTAINER_FACTORY_QUEUE)
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;

    }


}