package com.blueskykong.flyway.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keets
 * @data 2018/8/15.
 */
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("test")
    public String hello() {
        return "hello";
    }
}
