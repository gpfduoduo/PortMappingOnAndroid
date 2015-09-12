/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002
 *
 * File: Subscriber.java
 *
 * Revision;
 *
 * 01/29/03 - first revision. 07/31/04 - Added isExpired(). 10/26/04 - Oliver
 * Newell <newell@media-rush.com> - Added support the intinite time and fixed a
 * bug in isExpired().
 *
 ******************************************************************/

package org.cybergarage.upnp.event;


import java.net.URL;


public class Subscriber
{
    ////////////////////////////////////////////////
    //	Constructor
    ////////////////////////////////////////////////

    private String SID = null;

    ////////////////////////////////////////////////
    //	SID
    ////////////////////////////////////////////////
    private String ifAddr = "";
    private String deliveryURL = "";
    private String deliveryHost = "";

    ////////////////////////////////////////////////
    //	deliveryURL
    ////////////////////////////////////////////////
    private String deliveryPath = "";
    private int deliveryPort = 0;
    private long timeOut = 0;

    ////////////////////////////////////////////////
    //	deliveryURL
    ////////////////////////////////////////////////
    private long subscriptionTime = 0;
    private long notifyCount = 0;

    public Subscriber()
    {
        renew();
    }

    public String getSID()
    {
        return SID;
    }

    public void setSID(String sid)
    {
        SID = sid;
    }

    public String getInterfaceAddress()
    {
        return ifAddr;
    }

    public void setInterfaceAddress(String addr)
    {
        ifAddr = addr;
    }

    public String getDeliveryURL()
    {
        return deliveryURL;
    }

    public void setDeliveryURL(String deliveryURL)
    {
        this.deliveryURL = deliveryURL;
        try
        {
            URL url = new URL(deliveryURL);
            deliveryHost = url.getHost();
            deliveryPath = url.getPath();
            deliveryPort = url.getPort();
        }
        catch (Exception e)
        {
        }
    }

    ////////////////////////////////////////////////
    //	Timeout
    ////////////////////////////////////////////////

    public String getDeliveryHost()
    {
        return deliveryHost;
    }

    public String getDeliveryPath()
    {
        return deliveryPath;
    }

    public int getDeliveryPort()
    {
        return deliveryPort;
    }

    public long getTimeOut()
    {
        return timeOut;
    }

    ////////////////////////////////////////////////
    //	SubscriptionTIme
    ////////////////////////////////////////////////

    public void setTimeOut(long value)
    {
        timeOut = value;
    }

    public boolean isExpired()
    {
        long currTime = System.currentTimeMillis();

        // Thanks for Oliver Newell (10/26/04)
        if (timeOut == Subscription.INFINITE_VALUE)
            return false;

        // Thanks for Oliver Newell (10/26/04)
        long expiredTime = getSubscriptionTime() + getTimeOut() * 1000;
        if (expiredTime < currTime)
            return true;

        return false;
    }

    public long getSubscriptionTime()
    {
        return subscriptionTime;
    }

    ////////////////////////////////////////////////
    //	SEQ
    ////////////////////////////////////////////////

    public void setSubscriptionTime(long time)
    {
        subscriptionTime = time;
    }

    public long getNotifyCount()
    {
        return notifyCount;
    }

    public void setNotifyCount(int cnt)
    {
        notifyCount = cnt;
    }

    public void incrementNotifyCount()
    {
        if (notifyCount == Long.MAX_VALUE)
        {
            notifyCount = 1;
            return;
        }
        notifyCount++;
    }

    ////////////////////////////////////////////////
    //	renew
    ////////////////////////////////////////////////

    public void renew()
    {
        setSubscriptionTime(System.currentTimeMillis());
        setNotifyCount(0);
    }

}
