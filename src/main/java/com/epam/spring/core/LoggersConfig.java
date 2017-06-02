package com.epam.spring.core;

import java.util.ArrayList;
import java.util.List;

import com.epam.spring.core.loggers.CacheFileEventLogger;
import com.epam.spring.core.loggers.CombinedEventLogger;
import com.epam.spring.core.loggers.ConsoleEventLogger;
import com.epam.spring.core.loggers.EventLogger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggersConfig
{
    @Bean(name="combinedEventLogger")
    public CombinedEventLogger combinedEventLogger()
    {
        List<EventLogger> loggers = new ArrayList<EventLogger>(); 
        loggers.add(new ConsoleEventLogger());
        loggers.add(new CacheFileEventLogger());
        return new CombinedEventLogger(loggers);
    }
}
