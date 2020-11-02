package com.springboot.archetype.api;

import com.springboot.archetype.dao.domain.test.Test;
import com.springboot.archetype.service.TestService;
import com.springboot.archetype.service.impl.ActiveMQConfig;
import com.springboot.archetype.service.impl.ActiveMQQueueProducer;
import com.springboot.archetype.service.impl.ActiveMQTopicPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangxiaohui
 * @date 2019/10/14 下午5:02
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private ActiveMQQueueProducer activeMQQueueProducer;
    @Autowired
    private ActiveMQTopicPublisher activeMQTopicPublisher;
    @Autowired
    private TestService testService;

    @RequestMapping("/get")
    public List<Test> getTestList() {
        log.info("11221");
        activeMQQueueProducer.sendQueueMessage(new ActiveMQQueue(ActiveMQConfig.JMS_QUEUE), "我是队列");
        activeMQTopicPublisher.sendTopicMessage(new ActiveMQTopic(ActiveMQConfig.JMS_TOPIC), "我是话题");
        return null;
    }
}
