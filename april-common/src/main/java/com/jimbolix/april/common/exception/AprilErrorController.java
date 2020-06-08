package com.jimbolix.april.common.exception;

import com.jimbolix.april.common.utils.R;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ruihui.li
 * @Date: 2020/6/5 15:16
 * @Description:
 */
public class AprilErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @ResponseBody
    @RequestMapping(ERROR_PATH)
    public R error(HttpServletRequest request, HttpServletResponse response) {
        return R.error(response.getStatus(), "请求" + request.getRequestURI() + "失败");
    }
}