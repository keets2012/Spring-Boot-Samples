package com.blueskykong.samples.async.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author keets
 * @data 2018-12-16.
 */
@Controller
public class BuzController {

    @ExceptionHandler({NullPointerException.class})
    public String exception(NullPointerException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return "null pointer exception";
    }

    @RequestMapping("test")
    public void test() {
        throw new NullPointerException("出错了！");
    }
}
