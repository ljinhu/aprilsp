package com.jimbolix.april.gateway.feign;

import com.jimbolix.april.common.utils.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/21 16:02
 * @Description: 用于抓取异常的容错处理类
 */
@Slf4j
public class AuthProviderFallBackFactory implements FallbackFactory<AuthProvider> {

    @Override
    public AuthProvider create(Throwable throwable) {
        return new AuthProvider() {
            @Override
            public R decide(String authentication, String url, String method) {
                log.error("@@@@调用鉴权接口异常{}@@@@", throwable.getMessage());
                return R.error();
            }
        };
    }
}