package com.blueskykong.samples.async.rest;

import com.blueskykong.samples.async.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.Future;

/**
 * @author keets
 * @date 2018-12-19.
 */
@RestController
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public String taskExecute() {
        try {
            Future<String> r1 = taskService.doTaskOne();
            Future<String> r2 = taskService.doTaskTwo();
            Future<String> r3 = taskService.doTaskThree();
            while (true) {
                if (r1.isDone() && r2.isDone() && r3.isDone()) {
                    log.info("execute all tasks");
                    break;
                }
                Thread.sleep(200);
            }
            log.info("\n" + r1.get() + "\n" + r2.get() + "\n" + r3.get());
        } catch (Exception e) {
            log.error("error executing task for {}", e.getMessage());
        }

        return "ok";
    }

}
