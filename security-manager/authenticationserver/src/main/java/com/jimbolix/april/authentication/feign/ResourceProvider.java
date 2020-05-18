package com.jimbolix.april.authentication.feign;

import com.jimbolix.april.authentication.domain.SysMenuEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description
 * @ClassName ResourceProvider
 * @Author liruihui
 * @date 2020.05.16 17:36
 */
@FeignClient(name = "renren-fast" ,fallback = ResourceProviderFallback.class,path = "/renren-fast")
public interface ResourceProvider {

    @GetMapping("/sys/menu/list")
    List<SysMenuEntity> allResources();

    @GetMapping("/sys/menu/userMenu/{userAccount}")
    List<SysMenuEntity> getUserMenuList(@PathVariable("userAccount") String userAccount);
}
