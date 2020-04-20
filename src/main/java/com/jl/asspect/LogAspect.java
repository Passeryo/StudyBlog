package com.jl.asspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author J-Lei
 * @date 2020/3/8 10:09
 */
@Aspect
@Component
public class LogAspect {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.jl.web.*.*(..))") //声明切面(任何类里面所有的方法)
    public void log(){ }

    @Before("log()") //在log方法前执行
    public  void doBefore(JoinPoint joinPoint){
        //从reqyuest获取 url 与 ip
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestlog = new RequestLog(url,ip,classMethod,args);

        logger.info("Request :"+ requestlog);

    }

    @After("log()") //在log方法后执行
    public  void doAfter(){
        logger.info("--------------------doAfter-----------------");
    }

    @AfterReturning(returning = "result" ,pointcut = "log()") //再返回之后执行
    public void doAfterRuturn (Object result){
        logger.info("Result : " + result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
