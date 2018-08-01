package com.blueskykong.config.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author keets
 * @date 2017/11/14
 */
@RestController
@RequestMapping("api")
public class TestResource {

    @Value("${cloud.version}")
    private String size;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test")
    public String from() throws InterruptedException {

        int num = (int) (Math.random() * 100);
        redisTemplate.opsForValue().get("123");

        return num + "";
    }

    @GetMapping("/set")
    public void set() throws InterruptedException {


        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForValue().set("keys" + i, "val" + i);
            Thread.sleep(100);
        }

    }
}
