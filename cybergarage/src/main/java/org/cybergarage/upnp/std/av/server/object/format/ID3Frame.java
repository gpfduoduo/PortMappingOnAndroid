/******************************************************************
 *
 * MediaServer for CyberLink
 *
 * Copyright (C) Satoshi Konno 2003
 *
 * File : ID3Frame
 *
 * Revision:
 *
 * 12/03/03 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.std.av.server.object.format;


public class ID3Frame
{
    ////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////

    public final static String TIT1 = "TIT1";
    public final static String TIT2 = "TIT2";
    public final static String TIT3 = "TIT3";

    public final static String TPE1 = "TPE1";
    public final static String TPE2 = "TPE2";
    public final static String TPE3 = "TPE3";
    public final static String TPE4 = "TPE4";

    ////////////////////////////////////////////////
    // Constroctor
    ////////////////////////////////////////////////
    private String id;

    ////////////////////////////////////////////////
    // ID
    ////////////////////////////////////////////////
    private int flag;
    private int size;
    private byte data[];

    ////////////////////////////////////////////////
    // Size
    ////////////////////////////////////////////////

    public ID3Frame()
    {
        setID("");
        setFlag(0);
        setSize(0);
    }

    public String getID()
    {
        return id;
    }

    public void setID(String val)
    {
        id = val;
    }

    ////////////////////////////////////////////////
    // Size
    ////////////////////////////////////////////////

    public int getFlag()
    {
        return flag;
    }

    public void setFlag(int val)
    {
        flag = val;
    }

    public int getSize()
    {
        return size;
    }

    ////////////////////////////////////////////////
    // Data
    ////////////////////////////////////////////////

    public void setSize(int val)
    {
        size = val;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte val[])
    {
        data = val;
    }

    public String getStringData()
    {
        return new String(data, 1, getSize() - 1);
    }
}
