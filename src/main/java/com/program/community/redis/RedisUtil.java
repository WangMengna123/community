package com.program.community.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static JedisPool pool;
    static {
        String host = "127.0.0.1";
        int port = 6379;
        //连接池 Redis POOL 基本配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);//最大连接数
        poolConfig.setMaxIdle(1);//最大空闲数
        //.......
        //连接池
        pool = new JedisPool(poolConfig, host, port);
    }
    public static Jedis getJedis() {
        return pool.getResource();
    }
    public static void closeJedis(Jedis jedis) {
        jedis.close();
    }
}
