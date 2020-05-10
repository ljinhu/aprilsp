package com.jimbolix.april.authorization.enhancher;

import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * @Description 自定义的token信息生成器
 * @ClassName AprilTokenEnhancer
 * @Author liruihui
 * @date 2020.05.10 19:55
 */
public class AprilTokenEnhancer implements TokenEnhancer {

    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String,Object> additionalInfo = Maps.newHashMap();
        additionalInfo.put("organization", authentication.getName());
        additionalInfo.put("project","aprilsp");
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
