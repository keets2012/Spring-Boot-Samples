package com.blueskykong.springboot2webfluxexception.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author keets
 * @data 2018/12/11.
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    protected final Log logger = LogFactory.getLog(getClass());


    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ErrorCode handleCustomException(Exception e) {
        System.out.println("===========" + e.getLocalizedMessage());
        logger.error(e.getMessage());
        return new ErrorCode("e","error" );
    }

/*    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public DefaultResult handleException(Exception e) {
        logger.error(e.getMessage());
        return DefaultResult.fail("内部错误");
    }*/
}