package com.jimbolix.april.gateway.appEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @Author: ruihui.li
 * @Date: 2020/4/28 16:15
 * @Description: 
 */
@Component
@Slf4j
public class ReloadRouteEventPublish implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
            this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishRouteChangeEvent(){
        log.info("@@@@发布路由更新事件@@@@");
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }
}