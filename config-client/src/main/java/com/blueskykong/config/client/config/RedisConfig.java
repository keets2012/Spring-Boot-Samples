package com.blueskykong.config.client.config;


import cn.superid.common.redis.RedisCacheConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by keets on 2016/12/5.
 */

@Configuration
@Import(RedisCacheConfig.class)
public class RedisConfig {
}
