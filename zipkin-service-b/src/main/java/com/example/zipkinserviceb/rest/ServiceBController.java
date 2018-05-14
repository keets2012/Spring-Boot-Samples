package com.example.zipkinserviceb.rest;

import com.example.zipkinserviceb.feign.ServiceAClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keets
 * @data 2018/4/10.
 */
@RestController
@RequestMapping("/api")
public class ServiceBController {

    @Autowired
    ServiceAClient serviceAClient;

    @GetMapping("/service-b")
    public String fromServiceB() {

        return "from serviceB";
    }
    @GetMapping("/service-a")
    public String fromServiceA() {
        return serviceAClient.fromA();
    }

}
