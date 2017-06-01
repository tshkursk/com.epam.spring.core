package com.epam.spring.core.beans;

public class Client
{
    private String id;
    private String fullName;
    private String greeting;

	public Client(String id, String fullName)
    {
        this.id = id;
        this.fullName = fullName;
    }

    public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

    public String getGreeting() {
		return greeting;
	} 

	public String getId()
    {
        return id;
    }

    public String getFullName()
    {
        return fullName;
    }
}
