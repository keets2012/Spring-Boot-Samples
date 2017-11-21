package com.blueskykong.config.client.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keets
 * @date 2017/11/14
 */
@RestController
@RefreshScope
@RequestMapping("api")
public class TestResource {

    @Value("${cloud.version}")
    private String size;

    @GetMapping("/test")
    public String from() {
        return this.size;
    }
}
