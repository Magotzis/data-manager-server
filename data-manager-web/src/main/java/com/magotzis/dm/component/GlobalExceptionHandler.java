package com.magotzis.dm.component;

import com.magotzis.dm.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author dengyq on 14:57 2018/1/16
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException e) {
        return new ModelAndView("/error")
                .addObject("statusCode", 400)
                .addObject("errorMessage", "参数非法");
    }

    @ExceptionHandler(BaseException.class)
    public ModelAndView handleBaseException(BaseException e) {
        return new ModelAndView("/error")
                .addObject("statusCode", 410)
                .addObject("errorMessage", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleDefaultException(Exception e) {
        LOGGER.error("未知异常", e);
        return new ModelAndView("/error")
                .addObject("statusCode", 500)
                .addObject("errorMessage", "未知异常,请联系开发者");
    }
}
