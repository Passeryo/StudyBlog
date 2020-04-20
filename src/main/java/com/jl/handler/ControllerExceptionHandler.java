package com.jl.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author J-Lei
 * @date 2020/3/7 15:21
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

// 异常处理注解
//级别设置
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler (HttpServletRequest request,Exception e){
//        记录错误日志
        logger.error("Request URL : {"+request.getRequestURL()+"} ,Exception : {"+e+"}");
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("Exception" + e);
        mv.setViewName("error/error");

        return mv;
    }
}
