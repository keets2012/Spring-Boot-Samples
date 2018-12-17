package com.blueskykong.samples.async.config;

import com.blueskykong.samples.async.constants.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author keets
 * @data 2018-12-16.
 */
@Component
public class CustomMvcExceptionHandler implements HandlerExceptionResolver {

    private ObjectMapper objectMapper;

    public CustomMvcExceptionHandler() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object o, Exception ex) {
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        Map<String, Object> map = new HashMap<>();
        if (ex instanceof NullPointerException) {
            map.put("code", ResponseCode.NP_EXCEPTION);
        } else if (ex instanceof IndexOutOfBoundsException) {
            map.put("code", ResponseCode.INDEX_OUT_OF_BOUNDS_EXCEPTION);
        } else {
            map.put("code", ResponseCode.CATCH_EXCEPTION);
        }
        try {
            map.put("data", ex.getMessage());
            response.getWriter().write(objectMapper.writeValueAsString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
