/******************************************************************
 *
 * MediaServer for CyberLink
 *
 * Copyright (C) Satoshi Konno 2003
 *
 * File : ContentNode
 *
 * Revision:
 *
 * 10/22/03 - first revision. 04/27/04 - Added setID(String) and
 * setParentID(String). - Changed getID() and getParentID() to return the string
 * value instead of interger. - Changed findContentNodeByID() to serach using a
 * string id.
 *
 ******************************************************************/

package org.cybergarage.upnp.std.av.server.object;


import java.io.PrintWriter;

import org.cybergarage.upnp.std.av.server.ContentDirectory;
import org.cybergarage.upnp.std.av.server.DC;
import org.cybergarage.upnp.std.av.server.MediaServer;
import org.cybergarage.upnp.std.av.server.UPnP;
import org.cybergarage.upnp.std.av.server.object.container.ContainerNode;
import org.cybergarage.upnp.std.av.server.object.item.ItemNode;
import org.cybergarage.xml.Attribute;
import org.cybergarage.xml.Node;


public abstract class ContentNode extends Node
{
    ////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////

    public final static String ID = "id";
    public final static String PARENT_ID = "parentID";
    public final static String RESTRICTED = "restricted";

    public final static String UNKNOWN = "UNKNOWN";

    ////////////////////////////////////////////////
    // Constroctor
    ////////////////////////////////////////////////
    private ContentDirectory contentDir;

    ////////////////////////////////////////////////
    // ContentDirectory
    ////////////////////////////////////////////////
    private ContentPropertyList propList = new ContentPropertyList();

    public ContentNode()
    {
        setID(0);
        setParentID(-1);
        setRestricted(1);
        setContentDirectory(null);
    }

    public ContentDirectory getContentDirectory()
    {
        return contentDir;
    }

    public void setContentDirectory(ContentDirectory cdir)
    {
        contentDir = cdir;
    }

    ////////////////////////////////////////////////
    // is*Node
    ////////////////////////////////////////////////

    public MediaServer getMediaServer()
    {
        return getContentDirectory().getMediaServer();
    }

    public boolean isContainerNode()
    {
        if (this instanceof ContainerNode)
            return true;
        return false;
    }

    ////////////////////////////////////////////////
    //	Property (Basic)
    ////////////////////////////////////////////////

    public boolean isItemNode()
    {
        if (this instanceof ItemNode)
            return true;
        return false;
    }

    public int getNProperties()
    {
        return propList.size();
    }

    public ContentProperty getProperty(int index)
    {
        return propList.getContentProperty(index);
    }

    public ContentProperty getProperty(String name)
    {
        return propList.getContentProperty(name);
    }

    public void addProperty(ContentProperty prop)
    {
        propList.add(prop);
    }

    public void insertPropertyAt(ContentProperty prop, int index)
    {
        propList.insertElementAt(prop, index);
    }

    public void addProperty(String name, String value)
    {
        ContentProperty prop = new ContentProperty(name, value);
        addProperty(prop);
    }

    public boolean removeProperty(ContentProperty prop)
    {
        return propList.remove(prop);
    }

    public boolean removeProperty(String name)
    {
        return removeProperty(getProperty(name));
    }

    public boolean hasProperties()
    {
        if (0 < getNProperties())
            return true;
        return false;
    }

    ////////////////////////////////////////////////
    //	Property (Extention)
    ////////////////////////////////////////////////

    public void setProperty(String name, String value)
    {
        ContentProperty prop = getProperty(name);
        if (prop != null)
        {
            prop.setValue(value);
            return;
        }
        prop = new ContentProperty(name, value);
        addProperty(prop);
    }

    public void setProperty(String name, int value)
    {
        setProperty(name, Integer.toString(value));
    }

    public void setProperty(String name, long value)
    {
        setProperty(name, Long.toString(value));
    }

    public String getPropertyValue(String name)
    {
        ContentProperty prop = getProperty(name);
        if (prop != null)
            return prop.getValue();
        return "";
    }

    public int getPropertyIntegerValue(String name)
    {
        String val = getPropertyValue(name);
        try
        {
            return Integer.parseInt(val);
        }
        catch (Exception e)
        {
        }
        return 0;
    }

    public long getPropertyLongValue(String name)
    {
        String val = getPropertyValue(name);
        try
        {
            return Long.parseLong(val);
        }
        catch (Exception e)
        {
        }
        return 0;
    }

    ////////////////////////////////////////////////
    //	Property Attribute (Extention)
    ////////////////////////////////////////////////

    public void setPropertyAttribure(String propName, String attrName, String value)
    {
        ContentProperty prop = getProperty(propName);
        if (prop == null)
        {
            prop = new ContentProperty(propName, "");
            addProperty(prop);
        }
        prop.setAttribute(attrName, value);
    }

    public String getPropertyAttribureValue(String propName, String attrName)
    {
        ContentProperty prop = getProperty(propName);
        if (prop != null)
            return prop.getAttributeValue(attrName);
        return "";
    }

    ////////////////////////////////////////////////
    // set
    ////////////////////////////////////////////////

    public abstract boolean set(Node node);

    ////////////////////////////////////////////////
    // ID
    ////////////////////////////////////////////////

    public void setID(int id)
    {
        setAttribute(ID, id);
    }

    public String getID()
    {
        return getAttributeValue(ID);
    }

    public void setID(String id)
    {
        setAttribute(ID, id);
    }

    ////////////////////////////////////////////////
    // parentID
    ////////////////////////////////////////////////

    public void setParentID(int id)
    {
        setAttribute(PARENT_ID, id);
    }

    public String getParentID()
    {
        return getAttributeValue(PARENT_ID);
    }

    public void setParentID(String id)
    {
        setAttribute(PARENT_ID, id);
    }

    ////////////////////////////////////////////////
    // parentID
    ////////////////////////////////////////////////

    public int getRestricted()
    {
        return getAttributeIntegerValue(RESTRICTED);
    }

    public void setRestricted(int id)
    {
        setAttribute(RESTRICTED, id);
    }

    ////////////////////////////////////////////////
    // dc:title
    ////////////////////////////////////////////////

    public String getTitle()
    {
        return getPropertyValue(DC.TITLE);
    }

    public void setTitle(String title)
    {
        setProperty(DC.TITLE, title);
    }

    ////////////////////////////////////////////////
    // upnp:class
    ////////////////////////////////////////////////

    public String getUPnPClass()
    {
        return getPropertyValue(UPnP.CLASS);
    }

    public void setUPnPClass(String title)
    {
        setProperty(UPnP.CLASS, title);
    }

    public boolean isUPnPClassStartWith(String targetClass)
    {
        if (targetClass == null)
            return false;
        String upnpClass = getUPnPClass();
        if (upnpClass == null)
            return false;
        return upnpClass.startsWith(targetClass);
    }

    ////////////////////////////////////////////////
    // upnp:writeStatus
    ////////////////////////////////////////////////

    public String getWriteStatus()
    {
        return getPropertyValue(UPnP.WRITE_STATUS);
    }

    public void setWriteStatus(String status)
    {
        setProperty(UPnP.WRITE_STATUS, status);
    }

    ////////////////////////////////////////////////
    //	toString 
    ////////////////////////////////////////////////

    private void outputPropertyAttributes(PrintWriter ps, ContentProperty prop)
    {
        int nAttributes = prop.getNAttributes();
        for (int n = 0; n < nAttributes; n++)
        {
            Attribute attr = prop.getAttribute(n);
            ps.print(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
        }
    }

    public void output(PrintWriter ps, int indentLevel, boolean hasChildNode)
    {
        String indentString = getIndentLevelString(indentLevel);

        String name = getName();
        String value = getValue();

        if (hasNodes() == false && hasProperties() == false)
        {
            ps.print(indentString + "<" + name);
            outputAttributes(ps);
            ps.println(">" + value + "</" + name + ">");
            return;
        }

        ps.print(indentString + "<" + name);
        outputAttributes(ps);
        ps.println(">");

        int nProps = getNProperties();
        for (int n = 0; n < nProps; n++)
        {
            String propIndentString = getIndentLevelString(indentLevel + 1);
            ContentProperty prop = getProperty(n);
            String propName = prop.getName();
            String propValue = prop.getValue();
            if (prop.hasAttributes() == true)
            {
                ps.print(propIndentString + "<" + propName);
                outputPropertyAttributes(ps, prop);
                ps.println(">" + propValue + "</" + propName + ">");
            }
            else
                ps.println(propIndentString + "<" + propName + ">" + propValue + "</"
                    + propName + ">");
        }

        if (hasChildNode == true)
        {
            int nChildNodes = getNNodes();
            for (int n = 0; n < nChildNodes; n++)
            {
                Node cnode = getNode(n);
                cnode.output(ps, indentLevel + 1, true);
            }
        }

        ps.println(indentString + "</" + name + ">");
    }

}
