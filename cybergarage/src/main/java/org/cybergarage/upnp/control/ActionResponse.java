/******************************************************************
 *
 * CyberUPnP for Java
 *
 * Copyright (C) Satoshi Konno 2002
 *
 * File: ActionResponse.java
 *
 * Revision;
 *
 * 01/29/03 - first revision. 09/02/03 - Giordano Sassaroli
 * <sassarol@cefriel.it> - Problem : Action Responses do not contain the
 * mandatory header field EXT - Error : ActionResponse class does not set the
 * EXT header
 *
 ******************************************************************/

package org.cybergarage.upnp.control;


import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPStatus;
import org.cybergarage.soap.SOAP;
import org.cybergarage.soap.SOAPResponse;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Service;
import org.cybergarage.xml.Node;


public class ActionResponse extends ControlResponse
{
    ////////////////////////////////////////////////
    //	Constructor
    ////////////////////////////////////////////////

    public ActionResponse()
    {
        setHeader(HTTP.EXT, "");
    }

    public ActionResponse(SOAPResponse soapRes)
    {
        super(soapRes);
        setHeader(HTTP.EXT, "");
    }

    ////////////////////////////////////////////////
    //	Response
    ////////////////////////////////////////////////

    private Node createResponseNode(Action action)
    {
        String actionName = action.getName();
        Node actionNameResNode = new Node(SOAP.METHODNS + SOAP.DELIM + actionName
            + SOAP.RESPONSE);

        Service service = action.getService();
        if (service != null)
        {
            actionNameResNode.setAttribute("xmlns:" + SOAP.METHODNS,
                service.getServiceType());
        }

        ArgumentList argList = action.getArgumentList();
        int nArgs = argList.size();
        for (int n = 0; n < nArgs; n++)
        {
            Argument arg = argList.getArgument(n);
            if (arg.isOutDirection() == false)
                continue;
            Node argNode = new Node();
            argNode.setName(arg.getName());
            argNode.setValue(arg.getValue());
            actionNameResNode.addNode(argNode);
        }

        return actionNameResNode;
    }

    private Node getActionResponseNode()
    {
        Node bodyNode = getBodyNode();
        if (bodyNode == null || bodyNode.hasNodes() == false)
            return null;
        return bodyNode.getNode(0);
    }

    ////////////////////////////////////////////////
    //	getResponse
    ////////////////////////////////////////////////

    public ArgumentList getResponse()
    {
        ArgumentList argList = new ArgumentList();

        Node resNode = getActionResponseNode();
        if (resNode == null)
            return argList;

        int nArgs = resNode.getNNodes();
        for (int n = 0; n < nArgs; n++)
        {
            Node node = resNode.getNode(n);
            String name = node.getName();
            String value = node.getValue();
            Argument arg = new Argument(name, value);
            argList.add(arg);
        }

        return argList;
    }

    public void setResponse(Action action)
    {
        setStatusCode(HTTPStatus.OK);

        Node bodyNode = getBodyNode();
        Node resNode = createResponseNode(action);
        bodyNode.addNode(resNode);

        Node envNode = getEnvelopeNode();
        setContent(envNode);
    }
}
