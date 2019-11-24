package com.program.community.redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisDemo {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public String getString(String key) {
        ValueOperations<String,String> str = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            System.out.println(str.get(key));
            return str.get(key);
        } else {
            String value = "123456";
            str.set(key, value);
            System.out.println(str.get(key));
            return str.get(key);
        }
    }

}
