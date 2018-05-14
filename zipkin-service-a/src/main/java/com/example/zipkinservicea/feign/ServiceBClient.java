package com.example.zipkinservicea.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author keets
 * @data 2018/4/10.
 */

@FeignClient("service-b")
public interface ServiceBClient {

    @RequestMapping(value = "/api/service-b")
    public String fromB();

}

