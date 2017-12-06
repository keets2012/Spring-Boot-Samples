package com.blueskykong.auth.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author keets
 * @date 2017/11/21
 */
@Path("/")
public class DemoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoResource.class);

    @POST
    @Path("test")
    @PreAuthorize("hasAuthority('CREATE_COMPANY')")
    public Response createAccount() {
        LOGGER.info("createAccount");
        return Response.status(Response.Status.CREATED).build();
    }
}
