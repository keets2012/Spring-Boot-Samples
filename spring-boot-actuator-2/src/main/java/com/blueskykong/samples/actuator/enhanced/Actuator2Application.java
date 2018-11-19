package com.blueskykong.samples.actuator.enhanced;

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
public class Actuator2Application {
    public static void main(String[] args) {
        SpringApplication.run(Actuator2Application.class, args);
    }


    @GetMapping("/test")
    public Boolean test() {
        return true;
    }
}
