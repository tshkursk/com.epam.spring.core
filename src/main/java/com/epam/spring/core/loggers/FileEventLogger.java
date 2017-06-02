package com.epam.spring.core.loggers;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.epam.spring.core.beans.Event;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileEventLogger implements EventLogger
{
    @Value("log.txt")
    private String fileName;

    private File file;

	public void logEvent(Event event)
    {
        try {
			FileUtils.writeStringToFile(file, event.toString(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@PostConstruct
    public void init() throws IOException
    {
        this.file = new File(fileName);
    }

    public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
