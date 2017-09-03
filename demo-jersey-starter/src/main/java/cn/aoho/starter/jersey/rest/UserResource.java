package cn.aoho.starter.jersey.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by keets on 2017/8/29.
 */

@Path("test")
@Api(value = "UserResource resource", produces = "application/json")
public class UserResource {

    @GET
    @Path("hello")
    @ApiOperation(value = "Gets a hello resource. World Version 1 (version in Accept Header)", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "hello resource found")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        Map<String, String> map = new HashMap();
        map.put("key", "hello, World");
        return Response.ok().entity(map).build();
    }
}
