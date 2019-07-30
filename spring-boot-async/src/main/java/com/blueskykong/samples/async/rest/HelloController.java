package com.blueskykong.samples.async.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author keets
 * @date 2018-12-19.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false) String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("name required!");
        }
        return name;
    }

}
