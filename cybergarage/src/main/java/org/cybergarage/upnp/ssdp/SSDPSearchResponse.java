/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002
 *
 * File: SSDPSearchResponse.java
 *
 * Revision;
 *
 * 01/14/03 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.ssdp;


import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPStatus;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.UPnP;


public class SSDPSearchResponse extends SSDPResponse
{
    ////////////////////////////////////////////////
    //	Constructor
    ////////////////////////////////////////////////

    public SSDPSearchResponse()
    {
        setStatusCode(HTTPStatus.OK);
        setCacheControl(Device.DEFAULT_LEASE_TIME);
        setHeader(HTTP.SERVER, UPnP.getServerName());
        setHeader(HTTP.EXT, "");
    }
}
