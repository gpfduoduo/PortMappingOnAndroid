/******************************************************************
 *
 * MediaServer for CyberLink
 *
 * Copyright (C) Satoshi Konno 2003
 *
 * File: ContentProperty.java
 *
 * Revision;
 *
 * 10/29/03 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.std.av.server.object;


import org.cybergarage.xml.Attribute;
import org.cybergarage.xml.AttributeList;


public class ContentProperty
{
    private String name = new String();
    private String value = new String();
    private AttributeList attrList = new AttributeList();

    public ContentProperty()
    {
    }

    ////////////////////////////////////////////////
    //	name
    ////////////////////////////////////////////////

    public ContentProperty(String name, String value)
    {
        setName(name);
        setValue(value);
    }

    public String getName()
    {
        return name;
    }

    ////////////////////////////////////////////////
    //	value
    ////////////////////////////////////////////////

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    ////////////////////////////////////////////////
    //	Attribute (Basic)
    ////////////////////////////////////////////////

    public void setValue(String value)
    {
        this.value = value;
    }

    public int getNAttributes()
    {
        return attrList.size();
    }

    public Attribute getAttribute(int index)
    {
        return attrList.getAttribute(index);
    }

    public Attribute getAttribute(String name)
    {
        return attrList.getAttribute(name);
    }

    public void addAttribute(Attribute attr)
    {
        attrList.add(attr);
    }

    public void insertAttributeAt(Attribute attr, int index)
    {
        attrList.insertElementAt(attr, index);
    }

    public void addAttribute(String name, String value)
    {
        Attribute attr = new Attribute(name, value);
        addAttribute(attr);
    }

    public boolean removeAttribute(Attribute attr)
    {
        return attrList.remove(attr);
    }

    public boolean removeAttribute(String name)
    {
        return removeAttribute(getAttribute(name));
    }

    public boolean hasAttributes()
    {
        if (0 < getNAttributes())
            return true;
        return false;
    }

    ////////////////////////////////////////////////
    //	Attribute (Extention)
    ////////////////////////////////////////////////

    public void setAttribute(String name, String value)
    {
        Attribute attr = getAttribute(name);
        if (attr != null)
        {
            attr.setValue(value);
            return;
        }
        attr = new Attribute(name, value);
        addAttribute(attr);
    }

    public void setAttribute(String name, int value)
    {
        setAttribute(name, Integer.toString(value));
    }

    public String getAttributeValue(String name)
    {
        Attribute attr = getAttribute(name);
        if (attr != null)
            return attr.getValue();
        return "";
    }

    public int getAttributeIntegerValue(String name)
    {
        String val = getAttributeValue(name);
        try
        {
            return Integer.parseInt(val);
        }
        catch (Exception e)
        {
        }
        return 0;
    }
}
