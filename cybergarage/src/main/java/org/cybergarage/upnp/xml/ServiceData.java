/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002-2003
 *
 * File: ServiceData.java
 *
 * Revision;
 *
 * 03/28/03 - first revision. 01/06/04 - Moved setQueryListener() and
 * getQueryListener() to StateVariableData class. 03/30/05 - Removed
 * setDescriptionURL() and getDescriptionURL().
 *
 ******************************************************************/

package org.cybergarage.upnp.xml;


import org.cybergarage.upnp.event.SubscriberList;
import org.cybergarage.util.ListenerList;
import org.cybergarage.xml.Node;


public class ServiceData extends NodeData
{
    private ListenerList controlActionListenerList = new ListenerList();

    ////////////////////////////////////////////////
    // controlActionListenerList
    ////////////////////////////////////////////////
    private Node scpdNode = null;
    private SubscriberList subscriberList = new SubscriberList();

    ////////////////////////////////////////////////
    // scpdNode
    ////////////////////////////////////////////////
    private String descriptionURL = "";
    private String sid = "";
    private long timeout = 0;

    ////////////////////////////////////////////////
    // SubscriberList
    ////////////////////////////////////////////////

    public ServiceData()
    {
    }

    public ListenerList getControlActionListenerList()
    {
        return controlActionListenerList;
    }

    ////////////////////////////////////////////////
    // SID
    ////////////////////////////////////////////////

    public Node getSCPDNode()
    {
        return scpdNode;
    }

    public void setSCPDNode(Node node)
    {
        scpdNode = node;
    }

    public SubscriberList getSubscriberList()
    {
        return subscriberList;
    }

    ////////////////////////////////////////////////
    // SID
    ////////////////////////////////////////////////

    public String getDescriptionURL()
    {
        return descriptionURL;
    }

    public void setDescriptionURL(String descriptionURL)
    {
        this.descriptionURL = descriptionURL;
    }

    public String getSID()
    {
        return sid;
    }

    ////////////////////////////////////////////////
    // Timeout
    ////////////////////////////////////////////////

    public void setSID(String id)
    {
        sid = id;
    }

    public long getTimeout()
    {
        return timeout;
    }

    public void setTimeout(long value)
    {
        timeout = value;
    }

}
