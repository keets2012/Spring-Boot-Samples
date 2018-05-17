package com.blueskykong.springboot.rabbitmq;

import com.blueskykong.springboot.rabbitmq.entity.TransactionMsg;
import com.blueskykong.springboot.rabbitmq.stream.StreamSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqApplicationTests {

    @Autowired
    private StreamSender streamSender;

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void girlList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/tx-test"))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().string("abc"));
    }

    @Test
    public void contextLoads() {
        TransactionMsg msg = new TransactionMsg();
        msg.setTarget("tx-test");
        streamSender.sendMsg(msg);
    }

}
