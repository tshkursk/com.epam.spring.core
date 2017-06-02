package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.loggers.EventType;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App
{
    @Autowired
    @Qualifier("client")
    private Client client;

    private Event event;

    @Autowired
    @Qualifier("cacheFileEventLogger")
	private EventLogger defaultLogger;

    private Map<EventType, EventLogger> loggerMap;

    @Resource
    @Qualifier("loggerMap")
    public void setLoggerMap(Map<EventType, EventLogger> loggerMap)
    {
        this.loggerMap = loggerMap;
    }

    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, LoggersConfig.class);
//        ctx.scan("com.epam.spring.core");
        app.logEvent(EventType.ERROR, "Some error event for user 1");
        app.logEvent(EventType.INFO, "Some event for user 1"); 
        context.close();
    }

    private void logEvent(EventType type, String message)
    {
    	EventLogger logger = loggerMap.get(type);
    	if (null == logger) logger = defaultLogger;
    	event.setMessage(client.getGreeting() + message.replaceAll(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }
}
