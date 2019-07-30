package com.blueskykong.springboot2webfluxexception.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Component
public class TimeHandler {
    public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
        String timeType = serverRequest.queryParam("time").get();
        return getTimeByType(timeType).flatMap(s -> ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN).syncBody(s))
                .onErrorResume(e -> Mono.just("Error: " + e.getMessage()).flatMap(s -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).syncBody(s)));
    }

    private Mono<String> getTimeByType(String timeType) {
        String type = Optional.ofNullable(timeType).orElse(
                "Now"
        );
        switch (type) {
            case "Now":
                return Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            case "Today":
                return Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            default:
                return Mono.empty();
        }
    }

    public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
        String timeType = serverRequest.queryParam("time").get();
        return getTimeByType(timeType)
                .onErrorReturn("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .flatMap(s -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN).syncBody(s));
    }


    public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(  // 1
                Flux.interval(Duration.ofSeconds(1)).   // 2
                        map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }
}



