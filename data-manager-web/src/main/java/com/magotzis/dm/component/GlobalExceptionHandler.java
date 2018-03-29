package com.magotzis.dm.component;

import com.magotzis.dm.exception.BaseException;
import com.magotzis.dm.util.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author dengyq on 14:57 2018/1/16
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultMap handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResultMap()
                .addAttribute("statusCode", 400)
                .addAttribute("message", "参数非法");

    }

    @ExceptionHandler(BaseException.class)
    public ResultMap handleBaseException(BaseException e) {
        return new ResultMap()
                .addAttribute("statusCode", 410)
                .addAttribute("message", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultMap handleDefaultException(Exception e) {
        LOGGER.error("未知异常", e);
        return new ResultMap()
                .addAttribute("statusCode", 500)
                .addAttribute("message", "未知异常,请联系开发者");
    }
}
