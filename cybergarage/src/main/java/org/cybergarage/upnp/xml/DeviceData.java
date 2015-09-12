/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002-2003
 *
 * File: DeviceData.java
 *
 * Revision;
 *
 * 03/28/03 - first revision. 12/25/03 - Added Advertiser functions.
 *
 ******************************************************************/

package org.cybergarage.upnp.xml;


import java.io.File;
import java.net.InetAddress;

import org.cybergarage.http.HTTPServerList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.UPnP;
import org.cybergarage.upnp.device.Advertiser;
import org.cybergarage.upnp.ssdp.SSDP;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.upnp.ssdp.SSDPSearchSocketList;
import org.cybergarage.util.ListenerList;


public class DeviceData extends NodeData
{
    private String descriptionURI = null;

    ////////////////////////////////////////////////
    // description
    ////////////////////////////////////////////////
    private File descriptionFile = null;
    private String location = "";
    private int leaseTime = Device.DEFAULT_LEASE_TIME;
    private HTTPServerList httpServerList = null;
    private InetAddress[] httpBinds = null;
    private int httpPort = Device.HTTP_DEFAULT_PORT;

    ////////////////////////////////////////////////
    // description
    ////////////////////////////////////////////////
    private ListenerList controlActionListenerList = new ListenerList();
    private SSDPSearchSocketList ssdpSearchSocketList = null;
    private String ssdpMulticastIPv4 = SSDP.ADDRESS;

    ////////////////////////////////////////////////
    //	LeaseTime 
    ////////////////////////////////////////////////
    private String ssdpMulticastIPv6 = SSDP.getIPv6Address();
    private int ssdpPort = SSDP.PORT;
    private InetAddress[] ssdpBinds = null;

    ////////////////////////////////////////////////
    //	HTTPServer 
    ////////////////////////////////////////////////
    private SSDPPacket ssdpPacket = null;
    private Advertiser advertiser = null;

    public DeviceData()
    {
    }

    public File getDescriptionFile()
    {
        return descriptionFile;
    }

    public void setDescriptionFile(File descriptionFile)
    {
        this.descriptionFile = descriptionFile;
    }

    ////////////////////////////////////////////////
    //	httpPort 
    ////////////////////////////////////////////////

    public String getDescriptionURI()
    {
        return descriptionURI;
    }

    public void setDescriptionURI(String descriptionURI)
    {
        this.descriptionURI = descriptionURI;
    }

    public String getLocation()
    {
        return location;
    }

    ////////////////////////////////////////////////
    // controlActionListenerList
    ////////////////////////////////////////////////

    public void setLocation(String location)
    {
        this.location = location;
    }

    public int getLeaseTime()
    {
        return leaseTime;
    }

    /*
     * public void setControlActionListenerList(ListenerList
     * controlActionListenerList) { this.controlActionListenerList =
     * controlActionListenerList; }
     */

    ////////////////////////////////////////////////
    // SSDPSearchSocket
    ////////////////////////////////////////////////

    public void setLeaseTime(int val)
    {
        leaseTime = val;
    }

    public HTTPServerList getHTTPServerList()
    {
        if (this.httpServerList == null)
        {
            this.httpServerList = new HTTPServerList(this.httpBinds, this.httpPort);
        }
        return this.httpServerList;
    }

    public InetAddress[] getHTTPBindAddress()
    {
        return this.httpBinds;
    }

    public void setHTTPBindAddress(InetAddress[] inets)
    {
        this.httpBinds = inets;
    }

    public int getHTTPPort()
    {
        return httpPort;
    }

    public void setHTTPPort(int port)
    {
        httpPort = port;
    }

    public ListenerList getControlActionListenerList()
    {
        return controlActionListenerList;
    }

    public SSDPSearchSocketList getSSDPSearchSocketList()
    {
        if (this.ssdpSearchSocketList == null)
        {
            this.ssdpSearchSocketList = new SSDPSearchSocketList(this.ssdpBinds,
                ssdpPort, ssdpMulticastIPv4, ssdpMulticastIPv6);
        }
        return ssdpSearchSocketList;
    }

    /**
     *
     * @return The port used for binding the SSDP service. The port will be used
     *         as source port for all SSDP messages
     */
    public int getSSDPPort()
    {
        return this.ssdpPort;
    }

    /**
     *
     * @param port The port to use for binding the SSDP service. The port will
     *            be used as source port for all SSDP messages
     * @since 1.8
     */
    public void setSSDPPort(int port)
    {
        this.ssdpPort = port;
    }

    /**
     *
     * @return inets The <tt>InetAddress</tt> that will be binded for this
     *         service <code>null</code> means that defulat behaviur will be
     *         used
     * @since 1.8
     */
    public InetAddress[] getSSDPBindAddress()
    {
        return this.ssdpBinds;
    }

    /**
     *
     * @param inets The <tt>InetAddress</tt> that will be binded for listing
     *            this service. Use <code>null</code> for the default behaviur.
     * @see {@link UPnP}
     * @see {@link USSDP}
     * @see {@link HostInterface}
     * @since 1.8
     */
    public void setSSDPBindAddress(InetAddress[] inets)
    {
        this.ssdpBinds = inets;
    }

    /**
     *
     * @return The IPv4 address used for Multicast comunication
     */
    public String getMulticastIPv4Address()
    {
        return this.ssdpMulticastIPv4;
    }

    /**
     *
     * @param ip The IPv4 address used as destination address for Multicast
     *            comunication
     * @since 1.8
     */
    public void setMulticastIPv4Address(String ip)
    {
        this.ssdpMulticastIPv4 = ip;
    }

    ////////////////////////////////////////////////
    // SSDPPacket
    ////////////////////////////////////////////////

    /**
     *
     * @return The IPv6 address used as destination address for Multicast
     *         comunication
     * @since 1.8
     */
    public String getMulticastIPv6Address()
    {
        return this.ssdpMulticastIPv6;
    }

    /**
     *
     * @param ip The IPv6 address used as destination address for Multicast
     *            comunication
     * @since 1.8
     */
    public void setMulticastIPv6Address(String ip)
    {
        this.ssdpMulticastIPv6 = ip;
    }

    public SSDPPacket getSSDPPacket()
    {
        return ssdpPacket;
    }

    ////////////////////////////////////////////////
    // Advertiser
    ////////////////////////////////////////////////

    public void setSSDPPacket(SSDPPacket packet)
    {
        ssdpPacket = packet;
    }

    public Advertiser getAdvertiser()
    {
        return advertiser;
    }

    public void setAdvertiser(Advertiser adv)
    {
        advertiser = adv;
    }

}
