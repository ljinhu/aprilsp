package com.jimbolix.april.common.constant;
/**
 * @Author: ruihui.li
 * @Date: 2020/4/26 08:16
 * @Description: 
 */
public interface RabbitMqConstants {

    String route_gateway_add_queue_name = "route_gateway_add_queue_name";
    String route_gateway_del_queue_name = "route_gateway_del_queue_name";

    String route_gateway_topic_exchange = "route_gateway_topic_exchange";

    String route_gateway_add_bingding_key = "route_gateway_add_bingding.*";
    String route_gateway_del_bingding_key = "route_gateway_del_bingding.*";
}