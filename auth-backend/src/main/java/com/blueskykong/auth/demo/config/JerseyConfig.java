package com.blueskykong.auth.demo.config;


import com.blueskykong.auth.demo.filter.CustomAuthorizationFilter;
import com.blueskykong.auth.demo.rest.DemoResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


/**
 * @author keets
 * @date 2017/9/5
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RequestContextFilter.class);
        register(CustomAuthorizationFilter.class);
        register(DemoResource.class);
    }
}