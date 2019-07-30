package com.blueskykong.spring.cloud.gateway.filter;

import com.google.gson.Gson;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CtokenGatewayFilter extends AbstractGatewayFilterFactory<CtokenGatewayFilter.Config> {


    public CtokenGatewayFilter() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerRequest serverRequest = new DefaultServerRequest(exchange);

            System.out.println("访问路径============" + serverRequest.uri().getPath());
            HttpHeaders headers = exchange.getRequest().getHeaders();

            List<String> errorList = headers.get("bodystr");


            if (null == errorList || errorList.size() == 0) {
                Flux<HashMap> bodyToFlux = serverRequest.bodyToFlux(config.inClass);

                Gson gson = new Gson();

                bodyToFlux.map(o -> {

                    System.out.println("class=======" + o.getClass());
                    System.out.println("o 的value=====" + gson.toJson(o));
                    return o;
                }).subscribe();
            }
            return chain.filter(exchange);
        };
    }


    @Override
    public String name() {
        return "ctoken";
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("ctoken");
    }


    public static class Config {

        private boolean enabled;
        private Class inClass = HashMap.class;
        private Class outClass = HashMap.class;

        private RewriteFunction rewriteFunction = (exchange, bodyMap) -> {


            HashMap<String, Object> valueMap = (HashMap<String, Object>) bodyMap;
            String vin = (String) valueMap.get("vin");

            System.out.println("uname===========" + valueMap.get("uname"));
            valueMap.put("name", "zhangsan");
            valueMap.put("age", 18);

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("msg", "校验错误");
            resultMap.put("result", 99);


            return Mono.just(resultMap);
        };

        public RewriteFunction getRewriteFunction() {
            return rewriteFunction;
        }

        public void setRewriteFunction(RewriteFunction rewriteFunction) {
            this.rewriteFunction = rewriteFunction;
        }

        public Class getInClass() {
            return inClass;
        }

        public void setInClass(Class inClass) {
            this.inClass = inClass;
        }

        public Class getOutClass() {
            return outClass;
        }

        public void setOutClass(Class outClass) {
            this.outClass = outClass;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

}
