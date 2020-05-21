package com.jimbolix.april.gateway.feign;

import com.jimbolix.april.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @ClassName AuthProvider
 * @Author liruihui
 * @date 2020.05.17 14:50
 */
@FeignClient(name = "authenticationserver",fallbackFactory = AuthProviderFallBackFactory.class)
public interface AuthProvider {

    @PostMapping(value = "/auth/permission")
    R decide(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("url") String url, @RequestParam("method") String method);
}
