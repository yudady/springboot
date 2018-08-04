package com.tommy.event;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("......ApplicationStartedEvent......");
    }

}