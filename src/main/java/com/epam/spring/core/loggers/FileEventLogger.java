package com.epam.spring.core.loggers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileEventLogger implements EventLogger
{
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

    public void init() throws IOException
    {
        this.file = new File(fileName);
    }

    public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
