package com.lms.LMS.model;

public class AuthRequest
{
    private String username;
    private String password;

    public AuthRequest(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPasswod(String password)
    {
        this.password = password;
    }
}
