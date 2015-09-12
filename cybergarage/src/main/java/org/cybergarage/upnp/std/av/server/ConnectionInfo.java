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
 * 06/19/04 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.std.av.server;


public class ConnectionInfo
{
    ////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////

    public final static String INPUT = "Input";
    public final static String OUTPUT = "Output";
    public final static String OK = "OK";
    public final static String UNKNOWN = "Unknown";

    ////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////
    private int id;

    ////////////////////////////////////////////////
    // ID
    ////////////////////////////////////////////////
    private int rcsId;
    private int transId;
    private String protocolInfo;

    ////////////////////////////////////////////////
    // RcsID
    ////////////////////////////////////////////////
    private String peerConnectionManager;
    private int peerConnectionID;
    private String direction;

    ////////////////////////////////////////////////
    // AVTransportID
    ////////////////////////////////////////////////
    private String status;

    public ConnectionInfo(int id)
    {
        setID(id);
        setRcsID(-1);
        setAVTransportID(-1);
        setProtocolInfo("");
        setPeerConnectionManager("");
        setPeerConnectionID(-1);
        setDirection(OUTPUT);
        setStatus(UNKNOWN);
    }

    public int getID()
    {
        return id;
    }

    ////////////////////////////////////////////////
    // ProtocolInfo
    ////////////////////////////////////////////////

    public void setID(int value)
    {
        id = value;
    }

    public int getRcsID()
    {
        return rcsId;
    }

    public void setRcsID(int value)
    {
        rcsId = value;
    }

    ////////////////////////////////////////////////
    // PeerConnectionManager
    ////////////////////////////////////////////////

    public int getAVTransportID()
    {
        return transId;
    }

    public void setAVTransportID(int value)
    {
        transId = value;
    }

    public String getProtocolInfo()
    {
        return protocolInfo;
    }

    ////////////////////////////////////////////////
    // PeerConnectionID 
    ////////////////////////////////////////////////

    public void setProtocolInfo(String value)
    {
        protocolInfo = value;
    }

    public String getPeerConnectionManager()
    {
        return peerConnectionManager;
    }

    public void setPeerConnectionManager(String value)
    {
        peerConnectionManager = value;
    }

    ////////////////////////////////////////////////
    // Direction
    ////////////////////////////////////////////////

    public int getPeerConnectionID()
    {
        return peerConnectionID;
    }

    public void setPeerConnectionID(int value)
    {
        peerConnectionID = value;
    }

    public String getDirection()
    {
        return direction;
    }

    ////////////////////////////////////////////////
    // Status
    ////////////////////////////////////////////////

    public void setDirection(String value)
    {
        direction = value;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String value)
    {
        status = value;
    }
}
