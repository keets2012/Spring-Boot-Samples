package com.blueskykong.config.client.config;


import com.blueskykong.config.client.resource.TestResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


/**
 * @author keets
 * @date 2017/10/5
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RequestContextFilter.class);
        // Exception Mapper
        register(TestResource.class);
    }
}