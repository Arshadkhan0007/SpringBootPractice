package com.example.OrderMicroService.service;

public class HandlingToken {

    private static final ThreadLocal threadLocal=new ThreadLocal();

    public String getToken()
    {
        return threadLocal.get().toString();
    }


    public void setToken(String token)
    {
        threadLocal.set(token);
    }
}
