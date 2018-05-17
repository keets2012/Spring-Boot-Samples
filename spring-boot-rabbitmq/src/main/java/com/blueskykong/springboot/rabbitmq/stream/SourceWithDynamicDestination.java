package com.blueskykong.springboot.rabbitmq.stream;

import com.blueskykong.springboot.rabbitmq.entity.TransactionMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author keets
 * @data 2018/5/17.
 */
@EnableBinding
@Controller
public class SourceWithDynamicDestination {

    @Autowired
    private BinderAwareChannelResolver resolver;

    @RequestMapping(path = "/test/{target}", method = GET, consumes = "*/*")
    public void handleRequest(@PathVariable("target") String target) {
        Object contentType = "application/x-java-object;type=com.blueskykong.springboot.rabbitmq.entity.TransactionMsg";
        TransactionMsg msg = new TransactionMsg();
        msg.setTarget(target);
        sendMessage(msg, target, contentType);
    }

    private void sendMessage(Object body, String target, Object contentType) {
        resolver.resolveDestination(target).send(MessageBuilder.createMessage(body,
                new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, contentType))));
    }
}
