package com.jimbolix.april.gateway.service.impl;

import com.jimbolix.april.common.utils.R;
import com.jimbolix.april.gateway.feign.AuthProvider;
import com.jimbolix.april.gateway.service.AprilAuthService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * @Description
 * @ClassName AprilAuthServiceImpl
 * @Author liruihui
 * @date 2020.05.17 14:32
 */
@Service
@Slf4j
public class AprilAuthServiceImpl implements AprilAuthService {
    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";

    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Autowired
    private AuthProvider authProvider;

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(ignoreUrl));
    }

    @Override
    public boolean hasPermission(String authentication, String url, String method) {
        //首先判断是否有token
        if (StringUtils.isEmpty(authentication) || !authentication.startsWith(BEARER)) {
            return false;
        }
        //验证token是否失效
        if (this.invalidJwtAccessToken(authentication)) {
            return false;
        }
        return hasPermission(authProvider.decide(authentication, url, method));
    }

    @Override
    public boolean invalidJwtAccessToken(String authentication) {
        // 是否无效true表示无效
        boolean invalid = Boolean.TRUE;
        try {
            getJwt(authentication);
            invalid = Boolean.FALSE;
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException ex) {
            log.error("user token error :{}", ex.getMessage());
        }
        return invalid;
    }

    @Override
    public Jws<Claims> getJwt(String jwtToken) {
        if (jwtToken.startsWith(BEARER)) {
            jwtToken = StringUtils.substring(jwtToken, BEARER.length());
        }

        return Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(jwtToken);
    }

    private boolean hasPermission(R authResult) {
        Boolean result = (Boolean) authResult.get("result");
        log.debug("签权结果:{}", result);
        return result;
    }
}
