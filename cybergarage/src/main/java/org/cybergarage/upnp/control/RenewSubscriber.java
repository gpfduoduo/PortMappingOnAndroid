/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002
 *
 * File: RenewSubscriber.java
 *
 * Revision:
 *
 * 07/07/04 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.control;


import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.util.ThreadCore;


public class RenewSubscriber extends ThreadCore
{
    public final static long INTERVAL = 120;

    ////////////////////////////////////////////////
    //	Constructor
    ////////////////////////////////////////////////
    private ControlPoint ctrlPoint;

    ////////////////////////////////////////////////
    //	Member
    ////////////////////////////////////////////////

    public RenewSubscriber(ControlPoint ctrlp)
    {
        setControlPoint(ctrlp);
    }

    public ControlPoint getControlPoint()
    {
        return ctrlPoint;
    }

    public void setControlPoint(ControlPoint ctrlp)
    {
        ctrlPoint = ctrlp;
    }

    ////////////////////////////////////////////////
    //	Thread
    ////////////////////////////////////////////////

    public void run()
    {
        ControlPoint ctrlp = getControlPoint();
        long renewInterval = INTERVAL * 1000;
        while (isRunnable() == true)
        {
            try
            {
                Thread.sleep(renewInterval);
            }
            catch (InterruptedException e)
            {
            }
            ctrlp.renewSubscriberService();
        }
    }
}
