package com.guo.duoduo.getexternalipfromrouter;


import android.os.Handler;


public class MyController
{
    private String mName;

    public MyController(String name, Handler handler)
    {
        MyApplication.getInstance().getHandlerMap().put(name, handler);
        this.mName = name;
    }

    public void destroy()
    {
        MyApplication.getInstance().getHandlerMap().remove(mName);
    }
}
