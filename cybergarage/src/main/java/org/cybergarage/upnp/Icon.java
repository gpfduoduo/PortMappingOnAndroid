/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002
 *
 * File: Icon.java
 *
 * Revision;
 *
 * 11/28/02 - first revision. 04/12/06 - Added setUserData() and getUserData()
 * to set a user original data object.
 *
 ******************************************************************/

package org.cybergarage.upnp;


import org.cybergarage.xml.Node;


public class Icon
{
    ////////////////////////////////////////////////
    //	Constants
    ////////////////////////////////////////////////

    public final static String ELEM_NAME = "icon";

    ////////////////////////////////////////////////
    //	Member
    ////////////////////////////////////////////////
    private final static String MIME_TYPE = "mimeType";
    private final static String WIDTH = "width";

    ////////////////////////////////////////////////
    //	Constructor
    ////////////////////////////////////////////////
    private final static String HEIGHT = "height";
    private final static String DEPTH = "depth";

    ////////////////////////////////////////////////
    //	isIconNode
    ////////////////////////////////////////////////
    private final static String URL = "url";

    ////////////////////////////////////////////////
    //	mimeType
    ////////////////////////////////////////////////
    private Node iconNode;
    private Object userData = null;
    private byte bytes[] = null;

    public Icon(Node node)
    {
        iconNode = node;
    }

    ////////////////////////////////////////////////
    //	width
    ////////////////////////////////////////////////

    public Icon()
    {
        this(new Node(ELEM_NAME));
    }

    public static boolean isIconNode(Node node)
    {
        return Icon.ELEM_NAME.equals(node.getName());
    }

    public Node getIconNode()
    {
        return iconNode;
    }

    public String getMimeType()
    {
        return getIconNode().getNodeValue(MIME_TYPE);
    }

    ////////////////////////////////////////////////
    //	height
    ////////////////////////////////////////////////

    public void setMimeType(String value)
    {
        getIconNode().setNode(MIME_TYPE, value);
    }

    public boolean hasMimeType()
    {
        String iconMimeType = getMimeType();
        if (iconMimeType == null)
            return false;
        return (0 < iconMimeType.length()) ? true : false;
    }

    public void setWidth(String value)
    {
        getIconNode().setNode(WIDTH, value);
    }

    public int getWidth()
    {
        try
        {
            return Integer.parseInt(getIconNode().getNodeValue(WIDTH));
        }
        catch (Exception e)
        {
        };
        return 0;
    }

    ////////////////////////////////////////////////
    //	depth
    ////////////////////////////////////////////////

    public void setWidth(int value)
    {
        try
        {
            setWidth(Integer.toString(value));
        }
        catch (Exception e)
        {
        };
    }

    public void setHeight(String value)
    {
        getIconNode().setNode(HEIGHT, value);
    }

    public int getHeight()
    {
        try
        {
            return Integer.parseInt(getIconNode().getNodeValue(HEIGHT));
        }
        catch (Exception e)
        {
        };
        return 0;
    }

    public void setHeight(int value)
    {
        try
        {
            setHeight(Integer.toString(value));
        }
        catch (Exception e)
        {
        };
    }

    ////////////////////////////////////////////////
    //	URL
    ////////////////////////////////////////////////

    public void setDepth(String value)
    {
        getIconNode().setNode(DEPTH, value);
    }

    public int getDepth()
    {
        try
        {
            return Integer.parseInt(getIconNode().getNodeValue(DEPTH));
        }
        catch (Exception e)
        {
        };
        return 0;
    }

    public void setDepth(int value)
    {
        try
        {
            setDepth(Integer.toString(value));
        }
        catch (Exception e)
        {
        };
    }

    public String getURL()
    {
        return getIconNode().getNodeValue(URL);
    }

    public void setURL(String value)
    {
        getIconNode().setNode(URL, value);
    }

    ////////////////////////////////////////////////
    //	userData
    ////////////////////////////////////////////////

    public boolean hasURL()
    {
        String iconURL = getURL();
        if (iconURL == null)
            return false;
        return (0 < iconURL.length()) ? true : false;
    }

    public boolean isURL(String url)
    {
        if (url == null)
            return false;
        String iconURL = getURL();
        if (iconURL == null)
            return false;
        return iconURL.equals(url);
    }

    public Object getUserData()
    {
        return userData;
    }

    ////////////////////////////////////////////////
    //	Bytes
    ////////////////////////////////////////////////

    public void setUserData(Object data)
    {
        userData = data;
    }

    public boolean hasBytes()
    {
        return (bytes != null) ? true : false;
    }

    public byte[] getBytes()
    {
        return bytes;
    }

    public void setBytes(byte data[])
    {
        bytes = data;
    }
}
