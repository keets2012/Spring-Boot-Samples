package com.blueskykong.config.client.resource;

import cn.superid.common.redis.RedisLock;
import cn.superid.common.redis.RedisLockConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author keets
 * @date 2017/11/14
 */
@RestController
@RequestMapping("api")
public class TestResource {

    @Autowired
    RedisLockConfig redisLockConfig;

    @Value("${cloud.version}")
    private String size;

    @GetMapping("/test")
    public String from() {
        String key = "test_key_12345";
        RedisLock redisLock = redisLockConfig.newLock(key);

        try {
            redisLock.lock(10 * 3000L, TimeUnit.MILLISECONDS);
            Thread.sleep(100);
            System.out.println("is running ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redisLock.unlock();
        }
        return this.size;
    }
}
