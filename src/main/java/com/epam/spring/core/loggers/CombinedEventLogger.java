package com.epam.spring.core.loggers;

import java.util.List;

import com.epam.spring.core.beans.Event;

import org.springframework.stereotype.Component;

@Component
public class CombinedEventLogger implements EventLogger
{
	private List<EventLogger> loggers;

	public CombinedEventLogger(List<EventLogger> loggers) {
		this.loggers = loggers;
	}

	public void logEvent(Event event) {
		for (EventLogger logger: loggers) logger.logEvent(event);
	}

    public void setLoggers(List<EventLogger> loggers)
    {
        this.loggers = loggers;
    }
}
