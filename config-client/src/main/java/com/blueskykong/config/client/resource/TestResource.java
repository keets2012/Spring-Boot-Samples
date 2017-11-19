package com.blueskykong.config.client.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author keets
 * @date 2017/11/14
 */
@Path("/test")
@Component
@RefreshScope
public class TestResource {

    @Value("${cloud.version}")
    private String size;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getName() {
        System.out.println(size);
        return Response.ok(size).build();
    }
}
