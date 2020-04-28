package com.jimbolix.april.gateway.admin.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: ruihui.li
 * @Date: 2020/4/28 08:41
 * @Description: 用于接受前端参数
 */
@Data
public class GateWayRouteParam {

    private String id;
    /**
     * 路由id
     */
    private String routeId;
    /**
     * uri路径
     */
    private String uri;
    /**
     * 排序
     */
    private Integer orders;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态：Y-有效，N-无效
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 断言
     */
    private List<PredicateDefinition> predicates = new ArrayList<>();

    /**
     * 过滤器
     */
    private List<FilterDefinition> filters = new ArrayList<>();

    /**
     * po, persistence object, 持久层对象，对象的属性和数据库表的字段一一对应
     * bo, business object, 业务层对象
     * vo, view object, 表现层对象,对象的属性和页面展示的数据的名称一一对应
     * pojo, plain ordinary java object, 普通JAVA对象，只有属性及其set/get方法
     * dto，data transfer object，数据传输对象，用在需要跨进程或远程传输时
     *
     * @return
     */
    public GatewayRouteEntity toPo() throws JsonProcessingException {
        GatewayRouteEntity gatewayRouteEntity = new GatewayRouteEntity();
        BeanUtils.copyProperties(this, gatewayRouteEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        gatewayRouteEntity.setFilters(objectMapper.writeValueAsString(this.getFilters()));
        gatewayRouteEntity.setPredicates(objectMapper.writeValueAsString(this.getPredicates()));
        return gatewayRouteEntity;
    }
}