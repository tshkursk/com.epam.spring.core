package com.epam.spring.core.loggers;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger
{
    private int cacheSize;
    private List<Event> cache = new ArrayList<Event>();

    public CacheFileEventLogger(int cacheSize)
    {
        super();
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event)
    {
        cache.add(event);
        
        if (cache.size() == cacheSize)
        {
            writeEventsFromCache();
            cache.clear();
        }
    }
    
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
