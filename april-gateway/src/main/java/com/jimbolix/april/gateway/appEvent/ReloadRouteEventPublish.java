package com.jimbolix.april.gateway.appEvent;

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
public class ReloadRouteEventPublish implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
            this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishRouteChangeEvent(){
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }
}