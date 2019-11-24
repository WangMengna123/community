package com.program.community;

import com.program.community.redis.RedisDemo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest {
    @Autowired
    private RedisDemo demo;

    @Test
    public void test() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
//        RedisDemo demo = context.getBean(RedisDemo.class);

        String key = "teacher";
        System.out.println(demo.getString(key));
    }
}
