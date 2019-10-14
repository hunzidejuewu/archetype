package com.springboot.archetype.service;

import com.springboot.archetype.dao.domain.test.Test;

import java.util.List;

/**
 * @author wangxiaohui
 * @date 2019/10/14 下午4:59
 */
public interface TestService {

    List<Test> getTestList();
}
