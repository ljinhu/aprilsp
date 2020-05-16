package com.jimbolix.april.authorization.feign;

import com.jimbolix.april.authorization.entity.UserInfo;
import com.jimbolix.april.common.utils.R;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

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

    public UserInfo user(@PathVariable("uniqueAccount") String uniqueAccount){
        return new UserInfo();
    }
}