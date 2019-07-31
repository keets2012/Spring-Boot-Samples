package com.blueskykong.samples.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author keets
 * @data 2018/4/4.
 */
@Component
@Slf4j
@Async
public class TaskService {

    //    @Async("taskExecutor")
//    @Async
    public Future<String> doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成，耗时" + (end - start) + "毫秒");
    }

    //    @Async("taskExecutor")
//    @Async
    public Future<String> doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成，耗时" + (end - start) + "毫秒");
    }

    //    @Async("taskExecutor")
//    @Async
    public Future<String> doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成，耗时" + (end - start) + "毫秒");
    }
}
