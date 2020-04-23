package com.jimbolix.april.gateway.admin.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jimbolix.april.common.utils.PageUtils;
import com.jimbolix.april.common.utils.Query;

import com.jimbolix.april.gateway.admin.dao.GatewayRouteDao;
import com.jimbolix.april.gateway.admin.entity.GatewayRouteEntity;
import com.jimbolix.april.gateway.admin.service.GatewayRouteService;


@Service("gatewayRouteService")
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteDao, GatewayRouteEntity> implements GatewayRouteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GatewayRouteEntity> page = this.page(
                new Query<GatewayRouteEntity>().getPage(params),
                new QueryWrapper<GatewayRouteEntity>()
        );

        return new PageUtils(page);
    }

}