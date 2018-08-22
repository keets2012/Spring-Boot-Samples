package com.blueskykong.mybatis.controller;

import com.blueskykong.mybatis.entity.TestModel;
import com.blueskykong.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keets
 * @data 2018/8/22.
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/entity/{id}")
    public TestModel getEntity(@PathVariable Integer id) {

        return testService.getEntity(id);
    }

    @PostMapping("/entity")
    public Integer addEntity() {

        return testService.insertModel(new TestModel((int)(Math.random()*10000),"testName"));
    }

}
