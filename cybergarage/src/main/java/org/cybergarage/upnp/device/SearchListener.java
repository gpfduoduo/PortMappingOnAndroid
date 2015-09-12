/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002
 *
 * File: SearchListener.java
 *
 * Revision;
 *
 * 11/18/02b - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.device;


import org.cybergarage.upnp.ssdp.SSDPPacket;


public interface SearchListener
{
    public void deviceSearchReceived(SSDPPacket ssdpPacket);
}
