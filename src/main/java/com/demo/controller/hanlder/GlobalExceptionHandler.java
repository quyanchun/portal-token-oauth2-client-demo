package com.demo.controller.hanlder;

import com.dtflys.forest.logging.DefaultLogHandler;
import com.mingxing.domain.TResult;
import com.mingxing.exception.PortalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    private final static Logger logger = LoggerFactory.getLogger(DefaultLogHandler.class);

    /**
     * 权限码异常
     */
    @ExceptionHandler(PortalException.class)
    public TResult handleNotPermissionException(PortalException e, HttpServletRequest request)
    {
        logger.error("====== PortalException ====== \n code:{},mgs:{}",e.getCode(),e.getMessage());
        return TResult.error(e.getCode(), e.getMessage());
    }
}