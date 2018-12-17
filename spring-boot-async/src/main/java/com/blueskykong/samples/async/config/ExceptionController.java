package com.blueskykong.samples.async.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author keets
 * @data 2018-12-16.
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handlerRuntimeException(RuntimeException ex) {
        if (ex instanceof MaxUploadSizeExceededException) {
            return new ModelAndView("error").addObject("msg", "文件太大！");
        }
        return new ModelAndView("error").addObject("msg", "未知错误：" + ex);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerMaxUploadSizeExceededException(Exception ex) {
        if (ex != null) {
            return new ModelAndView("error").addObject("msg", ex);
        }

        return new ModelAndView("error").addObject("msg", "未知错误：" + ex);

    }
}
