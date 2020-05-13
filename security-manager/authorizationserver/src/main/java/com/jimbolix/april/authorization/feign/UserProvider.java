package com.jimbolix.april.authorization.feign;

import com.jimbolix.april.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Author: ruihui.li
 * @Date: 2020/5/11 11:25
 * @Description: 
 */
@FeignClient(value = "renren-fast",fallback = UserProviderFallBack.class,path = "/renren-fast")
public interface UserProvider {

    @GetMapping(value = "/sys/user/info/{userId}")
    R info(@PathVariable("userId") Long userId);
}