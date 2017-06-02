package com.epam.spring.core.loggers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import com.epam.spring.core.beans.Event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheFileEventLogger extends FileEventLogger
{
    @Value("10")
    private int cacheSize;

    private List<Event> cache = new ArrayList<Event>();

    public void logEvent(Event event)
    {
        cache.add(event);
        
        if (cache.size() == cacheSize)
        {
            writeEventsFromCache();
            cache.clear();
        }
    }
    
    @PreDestroy
    public void destroy()
    {
        if (!cache.isEmpty())
            writeEventsFromCache();
    }

    private void writeEventsFromCache()
    {
    	for (Event event : cache)
    		super.logEvent(event);
    }
}
