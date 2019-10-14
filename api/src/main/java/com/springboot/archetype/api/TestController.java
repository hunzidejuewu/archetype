package com.springboot.archetype.api;

import com.springboot.archetype.dao.domain.test.Test;
import com.springboot.archetype.service.TestService;
import lombok.extern.slf4j.Slf4j;
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
    private TestService testService;

    @RequestMapping("/get")
    public List<Test> getTestList() {
        log.info("11221");
        return testService.getTestList();
    }
}
