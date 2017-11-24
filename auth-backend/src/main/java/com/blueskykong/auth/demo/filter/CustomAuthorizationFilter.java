package com.blueskykong.auth.demo.filter;

import cn.superid.common.security.AccessType;
import cn.superid.common.security.SecurityConstants;
import cn.superid.common.security.SimpleGrantedAuthority;
import cn.superid.common.security.UserContext;
import com.blueskykong.auth.demo.client.FeignAuthClient;
import com.blueskykong.auth.demo.entity.Permission;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author keets
 */
@Component
public class CustomAuthorizationFilter implements ContainerResponseFilter {

    @Autowired
    private FeignAuthClient feignAuthClient;

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        HttpServletRequest request = (HttpServletRequest) containerRequestContext.getRequest();

        String roleId = request.getHeader(SecurityConstants.ROLE_ID_IN_HEADER);
        String userId = request.getHeader(SecurityConstants.USER_ID_IN_HEADER);

        if (StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(userId)) {
            UserContext userContext = new UserContext(Long.parseLong(userId), Long.parseLong(roleId));
            userContext.setAccessType(AccessType.ACCESS_TYPE_NORMAL);

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
