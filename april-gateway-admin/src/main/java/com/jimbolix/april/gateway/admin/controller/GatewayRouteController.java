package com.jimbolix.april.gateway.admin.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimbolix.april.gateway.admin.entity.GatewayRouteEntity;
import com.jimbolix.april.gateway.admin.service.GatewayRouteService;
import com.jimbolix.april.common.utils.PageUtils;
import com.jimbolix.april.common.utils.R;


/**
 * 网关路由表
 *
 * @author liruihui
 * @email liruihui
 * @date 2020-04-23 21:30:04
 */
@RestController
@RequestMapping("ware/gatewayroute")
public class GatewayRouteController {
    @Autowired
    private GatewayRouteService gatewayRouteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = gatewayRouteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
            GatewayRouteEntity gatewayRoute = gatewayRouteService.getById(id);

        return R.ok().put("gatewayRoute", gatewayRoute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GatewayRouteEntity gatewayRoute) {
            gatewayRouteService.save(gatewayRoute);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GatewayRouteEntity gatewayRoute) {
            gatewayRouteService.updateById(gatewayRoute);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids) {
            gatewayRouteService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
