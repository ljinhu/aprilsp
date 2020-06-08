package com.jimbolix.april.common.exception;

import com.jimbolix.april.common.utils.R;

/**
 * @Author: ruihui.li
 * @Date: 2020/6/5 11:32
 * @Description: 异常信息转换
 */
public class AprilExceptionResolver {

    public static R resolver(AprilApplicationException e){
        return R.error(e.getAprilError().getCode(),e.getAprilError().getDescription());
    }
}