package com.blueskykong.springboot.rabbitmq.listener;

import com.blueskykong.springboot.rabbitmq.entity.TransactionMsg;
import com.blueskykong.springboot.rabbitmq.stream.TestSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author keets
 */
@Component
@EnableBinding({TestSink.class})
public class StreamListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamListener.class);


    @org.springframework.cloud.stream.annotation.StreamListener(TestSink.INPUT)
    public void processSMS(Message<TransactionMsg> message) {
        LOGGER.info("===============consume notification message: =======================" + message.toString());
        process(message.getPayload());
    }

    private void process(TransactionMsg message) {
        try {
            if (Objects.nonNull(message)) {
                LOGGER.info("===============consume notification message: =======================" + message.toString());
                if (!StringUtils.isEmpty(message.getMethod())) {
                    System.out.println(message.getMethod());
                }
            }

        } catch (Exception e) {

            return;
        }
        return;
    }
}