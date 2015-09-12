/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002-2003
 *
 * File: ActionData.java
 *
 * Revision;
 *
 * 03/28/03 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.xml;


import org.cybergarage.xml.Node;


public class NodeData
{
    private Node node;

    ////////////////////////////////////////////////
    // Node
    ////////////////////////////////////////////////

    public NodeData()
    {
        setNode(null);
    }

    public Node getNode()
    {
        return node;
    }

    public void setNode(Node node)
    {
        this.node = node;
    }
}
