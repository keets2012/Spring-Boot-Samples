package com.blueskykong.spring.cloud.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author keets
 * @data 2018/9/20.
 */
@Component
public class LabelFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(LabelFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain chain) {
        URI uri = serverWebExchange.getRequest().getURI();
        String requestUri = uri.getPath();
        ServerHttpRequest serverHttpRequest = serverWebExchange.getRequest();
        String labels = resolveBodyFromRequest(serverHttpRequest);
        logger.info("labels: {}", labels);
        if (logger.isDebugEnabled()) {
            logger.debug("URI: {}, X-label: {}, Remote: {}:{}", requestUri, labels,
                    Objects.requireNonNull(serverWebExchange.getRequest().getRemoteAddress()).getAddress().getHostAddress(),
                    serverWebExchange.getRequest().getRemoteAddress().getPort());
        }
        return chain.filter(serverWebExchange);
    }

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();

        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取request body
        return bodyRef.get();
    }
}
