package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.loggers.Event;
import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.loggers.EventType;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    private Client client;
    private Event event;
	private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers)
    {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
        app.logEvent(EventType.ERROR, "Some error event for user 1");
        app.logEvent(EventType.INFO, "Some event for user 1"); 
        context.close();
    }

    private void logEvent(EventType type, String message)
    {
    	EventLogger logger = loggers.get(type);
    	if (null == logger) logger = defaultLogger;
    	event.setMessage(client.getGreeting() + message.replaceAll(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }

    public void setEvent(Event event) {
		this.event = event;
	}
}
