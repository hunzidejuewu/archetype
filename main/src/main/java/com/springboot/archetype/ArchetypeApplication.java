package com.springboot.archetype;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangxiaohui
 * @date 2019/10/14 下午4:16
 */
@SpringBootApplication
@MapperScan("com.springboot.archetype.dao.mapper")
public class ArchetypeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArchetypeApplication.class, args);
    }
}
