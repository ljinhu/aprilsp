package com.jimbolix.april.authentication.controller;

import com.jimbolix.april.authentication.domain.SysMenuEntity;
import com.jimbolix.april.authentication.feign.ResourceProvider;
import com.jimbolix.april.authentication.service.AuthenticationService;
import com.jimbolix.april.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @ClassName AuthenticationController
 * @Author liruihui
 * @date 2020.05.16 17:45
 */
@RestController
public class AuthenticationController {

    @Autowired
    private ResourceProvider resourceProvider;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/all")
    public List<SysMenuEntity> testAllRes(){
        return resourceProvider.allResources();
    }

    @PostMapping(value = "/auth/permission")
    public R decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
        boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return R.ok().put("result",decide);
    }
}
