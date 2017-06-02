package com.epam.spring.core;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.CacheFileEventLogger;
import com.epam.spring.core.loggers.CombinedEventLogger;
import com.epam.spring.core.loggers.ConsoleEventLogger;
import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.loggers.EventType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import(LoggersConfig.class)
public class AppConfig
{
    @Autowired
    @Qualifier("combinedEventLogger")
    private CombinedEventLogger combinedEventLogger;

    @Bean
    public Client client()
    {
        return new Client();
    }

    @Bean
    @Scope("prototype")
    public Event event()
    {
        return new Event(new Date(), DateFormat.getDateTimeInstance());
    }

    @Bean(name="loggerMap")
    public Map<EventType, EventLogger> loggerMap()
    {
        Map<EventType, EventLogger> loggers = new HashMap<EventType, EventLogger>();
        loggers.put(EventType.INFO, new ConsoleEventLogger());
        loggers.put(EventType.ERROR, combinedEventLogger);
        return loggers;
    }
}
