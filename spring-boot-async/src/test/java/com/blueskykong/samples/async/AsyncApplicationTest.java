package com.blueskykong.samples.async;

import com.blueskykong.samples.async.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AsyncApplicationTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void test() throws Exception {

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() ->callThread());
        t1.start();

        long end = System.currentTimeMillis();
        log.info(Thread.currentThread().getName());
        t1.join();

        log.info("任务全部完成，总耗时：" + (end - start) + "毫秒");

    }

    private void callThread() {
        try {
            long start = System.currentTimeMillis();

            Future<String> task1 = taskService.doTaskOne();
            Future<String> task2 = taskService.doTaskTwo();
            Future<String> task3 = taskService.doTaskThree();


            while (true) {

                if (task1.isDone() && task2.isDone() && task3.isDone()) {
                    String a = task1.get(100000, TimeUnit.MILLISECONDS);
                    log.info(a);
                    log.info("任务一完成，总耗时：" + (System.currentTimeMillis() - start) + "毫秒");
                    Thread.sleep(1000);

                    break;
                }
//            Thread.sleep(1000);
            }
        } catch (Exception e) {

        }

    }

}
