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


import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.control.ControlResponse;


public class ActionData extends NodeData
{
    private ActionListener actionListener = null;

    ////////////////////////////////////////////////
    // ActionListener
    ////////////////////////////////////////////////
    private ControlResponse ctrlRes = null;

    public ActionData()
    {
    }

    public ActionListener getActionListener()
    {
        return actionListener;
    }

    ////////////////////////////////////////////////
    // ControlResponse
    ////////////////////////////////////////////////

    public void setActionListener(ActionListener actionListener)
    {
        this.actionListener = actionListener;
    }

    public ControlResponse getControlResponse()
    {
        return ctrlRes;
    }

    public void setControlResponse(ControlResponse res)
    {
        ctrlRes = res;
    }

}
