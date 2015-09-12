/******************************************************************
 *
 * MediaServer for CyberLink
 *
 * Copyright (C) Satoshi Konno 2003-2004
 *
 * File : ConnectionInfo.java
 *
 * Revision:
 *
 * 02/22/08 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.std.av.renderer;


public class AVTransportInfo
{
    ////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////

    private int instanceID;

    ////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////
    private String uri;
    private String uriMetaData;

    public AVTransportInfo()
    {
        setInstanceID(0);
        setURI("");
        setURIMetaData("");
    }

    ////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////

    public int getInstanceID()
    {
        return instanceID;
    }

    public void setInstanceID(int instanceID)
    {
        this.instanceID = instanceID;
    }

    public String getURI()
    {
        return uri;
    }

    ////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////

    public void setURI(String uri)
    {
        this.uri = uri;
    }

    public String getURIMetaData()
    {
        return uriMetaData;
    }

    public void setURIMetaData(String uriMetaData)
    {
        this.uriMetaData = uriMetaData;
    }

}
