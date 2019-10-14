package com.springboot.archetype.service.impl;

import com.springboot.archetype.dao.domain.test.Test;
import com.springboot.archetype.dao.domain.test.TestCriteria;
import com.springboot.archetype.dao.mapper.test.TestMapper;
import com.springboot.archetype.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangxiaohui
 * @date 2019/10/14 下午5:00
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> getTestList() {
        return testMapper.selectByExample(new TestCriteria());
    }
}
