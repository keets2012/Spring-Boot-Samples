package com.blueskykong.samples.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keets
 * @date 2018/11/13
 */
@SpringBootApplication
@RestController
public class ActuatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    @Autowired
    LoginService loginService;

    @GetMapping("/test")
    public Boolean test() {
        return loginService.login("1", null);
    }
}
