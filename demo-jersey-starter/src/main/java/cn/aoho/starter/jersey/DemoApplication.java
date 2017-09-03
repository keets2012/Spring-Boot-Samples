package cn.aoho.starter.jersey;

import cn.keets.swagger.EnableSwagger2Doc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by keets on 2017/8/29.
 */
@SpringBootApplication
@EnableSwagger2Doc
public class DemoApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        LOGGER.info("Demo-jersey-starter is running ...");
    }
}
