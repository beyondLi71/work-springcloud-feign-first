package com.beyondli.common.tools.exception;

import com.beyondli.common.utils.uuid.UUIDUtils;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/8/1 下午5:10
 * @desc 全局异常捕捉并转换异常
 */
@Log4j
@RestControllerAdvice(basePackages = "com.beyondli.rest")
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.cloud.client.ipAddress}")
    private String ip;

    @Resource
    ExceptionManager exceptionManager;

    /**
     * 校验异常
     * @param e
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public String handlerException(ConstraintViolationException e) {
        String code = e.getConstraintViolations().stream().limit(1).map(vio -> vio.getMessageTemplate())
                 .collect(Collectors.toList()).get(0);
        PhantomException exception = exceptionManager.createByCode(code);
        exception.setStackTrace(e.getStackTrace());

        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        return phantomException.toString();
    }

    /**
     * 如果是自定义异常
     * @param e
     */
    @ExceptionHandler(PhantomException.class)
    public String handlerException(PhantomException e) {
        PhantomException phantomException = exceptionManager.create(e);
        log.error(logTraceInfo(phantomException));
        return phantomException.toString();
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    public String handleException(HystrixRuntimeException e) {
        String message = e.getFallbackException().getCause().getMessage();
        PhantomException exception = new PhantomException(UUIDUtils.getUUID(), appName, ip, "FALLBACK_EXCEPTION", message);
        exception.setStackTrace(e.getStackTrace());
        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        return phantomException.toString();
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        PhantomException exception = new PhantomException(UUIDUtils.getUUID(), appName, ip, "SYSTEM_ERR", e.getMessage());
        exception.setStackTrace(e.getStackTrace());
        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        log.error(logTraceInfo(e));
        return phantomException.toString();
    }
    @ExceptionHandler(ZuulException.class)
    public String zuulException(Exception e) {
        PhantomException exception = new PhantomException(UUIDUtils.getUUID(), appName, ip, "SYSTEM_ERR", e.getMessage());
        exception.setStackTrace(e.getStackTrace());
        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        log.error(logTraceInfo(e));
        return phantomException.toString();
    }

    /**
     * KingBoy实现异常栈信息打印，通过查看源码
     * @param e
     * @return
     */
    private String logTraceInfo(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        for (StackTraceElement traceElement : trace) {
            sb.append("\n\tat " + traceElement);
        }
        return sb.toString();
    }

}
