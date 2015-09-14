package com.guo.duoduo.getexternalipfromrouter;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.cybergarage.upnp.Device;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import com.guo.duoduo.getexternalipfromrouter.entity.MappingEntity;


/**
 * Created by 郭攀峰 on 2015/9/12.
 */
public class MyApplication extends Application
{
    private static MyApplication instance;
    private ConcurrentHashMap<String, Handler> mHandlerMap = new ConcurrentHashMap<>();

    public static Device curDevice = null;

    public static List<MappingEntity> itemList = new ArrayList<MappingEntity>();

    @Override
    public void onCreate()
    {
        super.onCreate();

        instance = this;

    }

    public static void sendMessage(Message msg)
    {
        for (Handler handler : getInstance().getHandlerMap().values())
        {
            handler.sendMessage(Message.obtain(msg));
        }
    }

    public ConcurrentHashMap<String, Handler> getHandlerMap()
    {
        return mHandlerMap;
    }

    public static MyApplication getInstance()
    {
        return instance;
    }

}
