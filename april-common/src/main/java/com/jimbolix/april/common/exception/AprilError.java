package com.jimbolix.april.common.exception;

/**
 * @Author: ruihui.li
 * @Date: 2020/6/5 11:35
 * @Description: 错误信息封装
 */
public class AprilError {

    private int code;
    private String description;

    public AprilError() {
    }

    public AprilError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}