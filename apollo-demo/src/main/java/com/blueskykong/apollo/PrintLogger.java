package com.blueskykong.apollo;

import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Service
public class PrintLogger {
    private static Logger logger = LoggerFactory.getLogger(PrintLogger.class);

    @ApolloJsonValue("${kk.v}")
    private String v;

    @PostConstruct
    public void printLogger() throws Exception {
        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                logger.error("=========" + v);
                logger.info("我是info级别日志");
                logger.error("我是error级别日志");
                logger.warn("我是warn级别日志");
                logger.debug("我是debug级别日志");
                TimeUnit.SECONDS.sleep(1);
            }
        });
    }
}

