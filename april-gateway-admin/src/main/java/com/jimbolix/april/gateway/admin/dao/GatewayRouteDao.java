package com.jimbolix.april.gateway.admin.dao;

import com.jimbolix.april.gateway.admin.entity.GatewayRouteEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网关路由表
 *
 * @author liruihui
 * @email liruihui
 * @date 2020-04-23 21:30:04
 */
@Mapper
public interface GatewayRouteDao extends BaseMapper<GatewayRouteEntity> {

}
