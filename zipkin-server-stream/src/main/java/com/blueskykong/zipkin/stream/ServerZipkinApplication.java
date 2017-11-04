package com.blueskykong.zipkin.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * Created by keets on 2017/10/31.
 */

@SpringBootApplication
//@EnableZipkinServer
@EnableZipkinStreamServer
public class ServerZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerZipkinApplication.class, args);
    }
}
