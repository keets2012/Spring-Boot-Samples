package cn.aoho.starter.jersey.config;

/**
 * Created by keets on 2017/6/28.
 */

import cn.aoho.starter.jersey.rest.UserResource;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RequestContextFilter.class);
        //register(AbstractExceptionMapper.class);
        //register(ServerExceptionMapper.class);
        // 配置 restful packages
        register(UserResource.class);
    }

    @PostConstruct
    public void init() {
        this.register(ApiListingResource.class, SwaggerSerializers.class);
    }
}