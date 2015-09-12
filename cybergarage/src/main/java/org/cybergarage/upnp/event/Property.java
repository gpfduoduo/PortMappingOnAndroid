/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002-2003
 *
 * File: Subscriber.java
 *
 * Revision;
 *
 * 01/29/03 - first revision. 05/22/03 - Giordano Sassaroli
 * <sassarol@cefriel.it> - Problem : the setName method does not set the name of
 * the property - Error : the method contains a bug: 06/18/03 - Fixed a bug when
 * a null value is received to the name and the value of property.
 *
 ******************************************************************/

package org.cybergarage.upnp.event;


public class Property
{
    ////////////////////////////////////////////////
    //	Constructor
    ////////////////////////////////////////////////

    private String name = "";

    ////////////////////////////////////////////////
    //	name
    ////////////////////////////////////////////////
    private String value = "";

    public Property()
    {
    }

    public String getName()
    {
        return name;
    }

    ////////////////////////////////////////////////
    //	value
    ////////////////////////////////////////////////

    public void setName(String val)
    {
        if (val == null)
            val = "";
        name = val;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String val)
    {
        if (val == null)
            val = "";
        value = val;
    }
}
