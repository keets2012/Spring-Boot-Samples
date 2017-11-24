package com.blueskykong.auth.demo.client;

import com.blueskykong.auth.demo.entity.Permission;
import com.blueskykong.auth.demo.entity.UserAccess;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author keets
 * @date 2017/11/21
 */
public interface FeignAuthClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/userPermissions?userId={userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Permission> getUserPermissions(@RequestParam("userId") String userId);

    @RequestMapping(method = RequestMethod.GET, value = "/api/userAccesses?userId={userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserAccess> getUserTenantAccessList(@RequestParam("userId") String userId);
}
