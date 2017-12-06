package com.blueskykong.auth.demo.config;

import com.blueskykong.auth.demo.filter.AuthorizationFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by keets on 2017/12/6.
 */
@Configuration
@EnableAutoConfiguration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(authorizationFilter());
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public AuthorizationFilter authorizationFilter() {
        return new AuthorizationFilter();
    }

}
