package com.blueskykong.auth.demo.filter;

import com.blueskykong.auth.demo.annotation.PreAuth;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.xml.ws.Response;

/**
 * Created by keets on 2017/12/7.
 */
@Component
@Aspect
public class AuthAspect {

    @Pointcut("@annotation(com.blueskykong.auth.demo.annotation.PreAuth)")
    private void cut() {
    }

    /**
     * 定制一个环绕通知
     *
     * @param joinPoint
     */
/*    @Around("cut()")
    public void advice(ProceedingJoinPoint joinPoint) {
        System.out.println("环绕通知之开始");
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("环绕通知之结束");
    }*/

    //当想获得注解里面的属性，可以直接注入改注解
    @Around("cut()&&@annotation(preAuth)")
    public Object record(ProceedingJoinPoint joinPoint, PreAuth preAuth) throws Throwable {
        String value = preAuth.value();
        System.out.println(preAuth.value());
//        boolean rl = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(value);
        if (!value.equals("CREATE_COMPANY")) {
            return joinPoint.proceed();
        }
        return "401";
    }

}

