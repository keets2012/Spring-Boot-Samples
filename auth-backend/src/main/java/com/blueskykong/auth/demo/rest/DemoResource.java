package com.blueskykong.auth.demo.rest;

import org.springframework.security.access.prepost.PreAuthorize;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author keets
 * @date 2017/11/21
 */
public class DemoResource {


    @POST
    @Path("organizations")
    @PreAuthorize("hasAuthority('CREATE_COMPANY')")
    public Response createAccount() {

        return Response.status(Response.Status.CREATED).build();
    }
}
