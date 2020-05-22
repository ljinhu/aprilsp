package com.jimbolix.april.authorization.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jimbolix.april.common.utils.R;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/22 10:05
 * @Description: 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = AprilOauthExceptionSerilizer.class)
public class AprilOauthException extends OAuth2Exception {

    private R r;

    public AprilOauthException(OAuth2Exception oauth2Exception){
        super(oauth2Exception.getSummary(),oauth2Exception);
        this.r = R.error(oauth2Exception.getHttpErrorCode(),oauth2Exception.getMessage());

    }
}