package com.epam.spring.core.loggers;

import java.util.List;

public class CombinedEventLogger implements EventLogger
{
	private List<EventLogger> loggers;

	public CombinedEventLogger(List<EventLogger> loggers) {
		this.loggers = loggers;
	}

	public void logEvent(Event event) {
		for (EventLogger logger: loggers) logger.logEvent(event);
	}
}
