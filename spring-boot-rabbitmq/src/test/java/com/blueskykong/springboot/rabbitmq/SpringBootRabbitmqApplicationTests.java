package com.blueskykong.springboot.rabbitmq;

import com.blueskykong.springboot.rabbitmq.stream.StreamSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqApplicationTests {

    @Autowired
    private StreamSender streamSender;

    @Test
    public void contextLoads() {
        streamSender.sendMsg("hello");
    }

}
