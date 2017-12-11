package com.blueskykong.auth.demo.filter;

import com.blueskykong.auth.demo.client.FeignAuthClient;
import com.blueskykong.auth.demo.constants.AccessType;
import com.blueskykong.auth.demo.constants.SecurityConstants;
import com.blueskykong.auth.demo.entity.Permission;
import com.blueskykong.auth.demo.security.SimpleGrantedAuthority;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author keets
 */
@Provider
public class CustomAuthorizationFilter implements ContainerRequestFilter {

    @Autowired
    private FeignAuthClient feignAuthClient;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String userId = containerRequestContext.getHeaderString(SecurityConstants.USER_ID_IN_HEADER);

        if (StringUtils.isNotEmpty(userId)) {
            UserContext userContext = new UserContext(UUID.fromString(userId));
            userContext.setAccessType(AccessType.ACCESS_TYPE_NORMAL);
            System.out.println(userContext);
            List<Permission> permissionList = feignAuthClient.getUserPermissions(userId);
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            for (Permission permission : permissionList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority();
                authority.setAuthority(permission.getPermission());
                authorityList.add(authority);
            }
            userContext.setAuthorities(authorityList);

            SecurityContextHolder.setContext(userContext);
        }
    }
}
