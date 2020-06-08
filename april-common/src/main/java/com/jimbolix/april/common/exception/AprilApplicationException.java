package com.jimbolix.april.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: ruihui.li
 * @Date: 2020/6/5 11:03
 * @Description: 
 */
@ResponseStatus(
        code = HttpStatus.BAD_REQUEST,
        reason = "No such Order"
)
public class AprilApplicationException extends Exception {

    public AprilApplicationException(){
        super();
    }

    public AprilApplicationException(AprilError aprilError){
        this.aprilError = aprilError;
    }
    private AprilError aprilError;

    public AprilError getAprilError() {
        return aprilError;
    }

    public void setAprilError(AprilError aprilError) {
        this.aprilError = aprilError;
    }
}