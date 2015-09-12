/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002-2003
 *
 * File:StateVariableData.java
 *
 * Revision;
 *
 * 02/05/03 - first revision. 01/06/04 - Added setQueryListener() and
 * getQueryListener().
 *
 ******************************************************************/

package org.cybergarage.upnp.xml;


import org.cybergarage.upnp.control.QueryListener;
import org.cybergarage.upnp.control.QueryResponse;


public class StateVariableData extends NodeData
{
    private String value = "";

    ////////////////////////////////////////////////
    // value
    ////////////////////////////////////////////////
    private QueryListener queryListener = null;
    private QueryResponse queryRes = null;

    public StateVariableData()
    {
    }

    ////////////////////////////////////////////////
    // QueryListener
    ////////////////////////////////////////////////

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public QueryListener getQueryListener()
    {
        return queryListener;
    }

    ////////////////////////////////////////////////
    // QueryResponse
    ////////////////////////////////////////////////

    public void setQueryListener(QueryListener queryListener)
    {
        this.queryListener = queryListener;
    }

    public QueryResponse getQueryResponse()
    {
        return queryRes;
    }

    public void setQueryResponse(QueryResponse res)
    {
        queryRes = res;
    }

}
