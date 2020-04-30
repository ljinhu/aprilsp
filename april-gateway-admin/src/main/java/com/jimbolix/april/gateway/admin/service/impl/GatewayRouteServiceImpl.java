package com.jimbolix.april.gateway.admin.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimbolix.april.common.constant.CacheKeysConstant;
import com.jimbolix.april.gateway.admin.entity.GateWayRouteParam;
import com.jimbolix.april.gateway.admin.sender.BusSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jimbolix.april.common.utils.PageUtils;
import com.jimbolix.april.common.utils.Query;

import com.jimbolix.april.gateway.admin.dao.GatewayRouteDao;
import com.jimbolix.april.gateway.admin.entity.GatewayRouteEntity;
import com.jimbolix.april.gateway.admin.service.GatewayRouteService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;

@Slf4j
@Service("gatewayRouteService")
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteDao, GatewayRouteEntity> implements GatewayRouteService {
    @CreateCache(cacheType = CacheType.REMOTE, name = CacheKeysConstant.gateway_routes)
    private Cache<String, RouteDefinition> gateWayRouteCache;

    @Autowired
    private BusSender busSender;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GatewayRouteEntity> page = this.page(
                new Query<GatewayRouteEntity>().getPage(params),
                new QueryWrapper<GatewayRouteEntity>()
        );
        IPage<GateWayRouteParam> paramIPage = new Page<>();
        BeanUtils.copyProperties(page, paramIPage);
        List<GatewayRouteEntity> records = page.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            List<GateWayRouteParam> collect = records.stream().map(GatewayRouteEntity::toVo).collect(Collectors.toList());
            paramIPage.setRecords(collect);
        }
        return new PageUtils(paramIPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(GatewayRouteEntity gatewayRouteEntity) {
        RouteDefinition routeDefinition = gatewayRouteConvertToDefinition(gatewayRouteEntity);
        //放入缓存
        gateWayRouteCache.PUT(routeDefinition.getId(), routeDefinition);
        //todo 发送消息
        busSender.send(routeDefinition);
        return this.save(gatewayRouteEntity);
    }

    /**
     * 将GatewayRouteEntity转换为Spring cloud gateway 的RouteDefinition
     * 方便向消息队列中发送消息
     *
     * @param gatewayRouteEntity
     * @return
     */
    private RouteDefinition gatewayRouteConvertToDefinition(GatewayRouteEntity gatewayRouteEntity) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRouteEntity.getRouteId());
        String uri = gatewayRouteEntity.getUri();
        URI u = URI.create(uri);
        routeDefinition.setUri(u);
        routeDefinition.setOrder(gatewayRouteEntity.getOrders());
        ObjectMapper objectMapper = new ObjectMapper();
        //将json抓换为类对象
        try {
            String filters = gatewayRouteEntity.getFilters();
            if (StringUtils.isNotEmpty(filters)) {
                TypeReference typeReference = new TypeReference<List<FilterDefinition>>() {
                };
                routeDefinition.setFilters(objectMapper.readValue(filters, typeReference));
            }
            String predicates = gatewayRouteEntity.getPredicates();
            if (StringUtils.isNotEmpty(predicates)) {
                TypeReference typeReference = new TypeReference<List<PredicateDefinition>>() {
                };
                routeDefinition.setPredicates(objectMapper.readValue(predicates, typeReference));
            }
        } catch (Exception e) {
            log.error("GatewayRouteEntity转换异常RouteDefinition异常" + e.getMessage());
        }
        return routeDefinition;
    }

    /**
     * 路由重新加载
     */
    @Override
    @PostConstruct
    public boolean overLoadRoute() {
        List<GatewayRouteEntity> routeEntityList = this.list();

        if (!CollectionUtils.isEmpty(routeEntityList)) {
            routeEntityList.stream().forEach(gatewayRouteEntity ->
                    gateWayRouteCache.PUT(gatewayRouteEntity.getRouteId(), gatewayRouteConvertToDefinition(gatewayRouteEntity))
            );
        }
        log.info("路由重载成功，共{}条路由数据", routeEntityList.size());
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteByIds(String[] ids) {
        //先查询所有信息，根据routeid删除缓存并通知gateway更新路由
        Collection<GatewayRouteEntity> gatewayRouteEntities = this.listByIds(Arrays.asList(ids));
        if (!CollectionUtils.isEmpty(gatewayRouteEntities)) {
            gatewayRouteEntities.forEach(gatewayRouteEntity ->
                    busSender.sendDelMessage(gatewayRouteEntity.getRouteId())
            );
        }
        boolean b = this.removeByIds(Arrays.asList(ids));
        return b;
    }
}