package com.program.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Assert;
import org.springframework.data.redis.core.RedisTemplate;
import org.unbescape.java.JavaEscape;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6379;
        //lalalalalala
        //lqqqqqqqqqq
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        JedisPool jedisPool = new JedisPool(poolConfig,host,port);
        Jedis jedis = jedisPool.getResource();
        long str = jedis.hset("teacher", "id", "123"); //返回long
        Assert.assertEquals(1, str);


    }
}
