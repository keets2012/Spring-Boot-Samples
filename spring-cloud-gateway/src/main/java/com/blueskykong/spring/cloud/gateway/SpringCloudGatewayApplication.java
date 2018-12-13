package com.blueskykong.spring.cloud.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringCloudGatewayApplication {

    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @RequestMapping("/fallbackcontroller")
    public Map<String, String> fallbackcontroller(@RequestParam("a") String a) {
        return Collections.singletonMap("from", "fallbackcontroller");
    }

    @GetMapping("/proxy/{id}")
    public Mono<ResponseEntity<Object>> proxyFoos(@PathVariable Integer id, ProxyExchange<Object> proxy) {
        return proxy.uri("http://user:" + port + "/foos/" + id).get();
    }
    @RestController
    static class TestController {
        @GetMapping("/foos/{id}")
        public Foo foo(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {
            String custom = headers.getFirst("X-Custom");
            return new Foo(id == 1 ? "foo" : custom != null ? custom : "bye");
        }
    }



    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Foo {
        private String name;

        public Foo() {
        }

        public Foo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
