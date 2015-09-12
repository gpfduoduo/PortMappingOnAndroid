/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002
 *
 * File: SubscriptionResponse.java
 *
 * Revision;
 *
 * 01/29/03 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.event;


import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPResponse;
import org.cybergarage.upnp.UPnP;


public class SubscriptionResponse extends HTTPResponse
{
    ////////////////////////////////////////////////
    //	Constructor
    ////////////////////////////////////////////////

    public SubscriptionResponse()
    {
        setServer(UPnP.getServerName());
    }

    public SubscriptionResponse(HTTPResponse httpRes)
    {
        super(httpRes);
    }

    ////////////////////////////////////////////////
    //	Error
    ////////////////////////////////////////////////

    public void setResponse(int code)
    {
        setStatusCode(code);
        setContentLength(0);
    }

    ////////////////////////////////////////////////
    //	Error
    ////////////////////////////////////////////////

    public void setErrorResponse(int code)
    {
        setStatusCode(code);
        setContentLength(0);
    }

    ////////////////////////////////////////////////
    //	SID
    ////////////////////////////////////////////////

    public String getSID()
    {
        return Subscription.getSID(getHeaderValue(HTTP.SID));
    }

    public void setSID(String id)
    {
        setHeader(HTTP.SID, Subscription.toSIDHeaderString(id));
    }

    ////////////////////////////////////////////////
    //	Timeout
    ////////////////////////////////////////////////

    public long getTimeout()
    {
        return Subscription.getTimeout(getHeaderValue(HTTP.TIMEOUT));
    }

    public void setTimeout(long value)
    {
        setHeader(HTTP.TIMEOUT, Subscription.toTimeoutHeaderString(value));
    }
}
