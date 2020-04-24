package com.jimbolix.april.gateway.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 网关路由表
 *
 * @author liruihui
 * @email liruihui
 * @date 2020-04-23 21:30:04
 */
@Data
@TableName("gateway_route")
public class GatewayRouteEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
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
     * 判定器
     */
    private String predicates;
    /**
     * 过滤器
     */
    private String filters;
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

}
