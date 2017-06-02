package com.epam.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Event
{
    private int id;
    private String message;
    private Date date;
    private DateFormat format;

    public Event(Date date, DateFormat format)
    {
        this.id = new Random().nextInt();
        this.date = date;
        this.format = format;
    }

    @Override
    public String toString()
    {
        return "Event [id=" + id + ", message=" + message + ", date=" + format.format(date) + "]";
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
