package com.jimbolix.april.authorization.oauth2;

import com.jimbolix.april.authorization.exception.AprilOauthException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/22 09:53
 * @Description:
 */
public class AprilResponseExceptionTranslator implements WebResponseExceptionTranslator<AprilOauthException> {

    public ResponseEntity<AprilOauthException> translate(Exception e) throws Exception {
        OAuth2Exception auth2Exception = (OAuth2Exception) e;
        return ResponseEntity.status(auth2Exception.getHttpErrorCode())
                .body(new AprilOauthException(auth2Exception));
    }
}