package com.jimbolix.april.authorization.feign;

import com.jimbolix.april.common.utils.R;
import org.springframework.stereotype.Component;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/11 11:26
 * @Description: 
 */
@Component
public class UserProviderFallBack implements UserProvider{

    public R info(Long userId) {
        return R.error("根据用户id获取用户信息异常");
    }
}