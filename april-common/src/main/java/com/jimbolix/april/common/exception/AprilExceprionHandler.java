package com.jimbolix.april.common.exception;

import com.jimbolix.april.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ruihui.li
 * @Date: 2020/6/5 11:12
 * @Description: 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class AprilExceprionHandler {

    @ExceptionHandler(AprilApplicationException.class)
    @ResponseBody
    public R aprilExceptionHandler(AprilApplicationException e, HttpServletRequest request) {
        return R.error(e.getAprilError().getCode(), e.getAprilError().getDescription());
    }
}