/******************************************************************
 *
 * CyberLink for Java
 *
 * Copyright (C) Satoshi Konno 2002-2004
 *
 * File: AllowedValueRange.java
 *
 * Revision:
 *
 * 03/27/04 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp;


import org.cybergarage.xml.Node;


public class AllowedValueRange
{
    ////////////////////////////////////////////////
    //	Constants
    ////////////////////////////////////////////////

    public final static String ELEM_NAME = "allowedValueRange";

    ////////////////////////////////////////////////
    //	Member
    ////////////////////////////////////////////////
    private final static String MINIMUM = "minimum";
    private final static String MAXIMUM = "maximum";

    ////////////////////////////////////////////////
    //	Constructor
    ////////////////////////////////////////////////
    private final static String STEP = "step";
    private Node allowedValueRangeNode;

    ////////////////////////////////////////////////
    //	isAllowedValueRangeNode
    ////////////////////////////////////////////////

    public AllowedValueRange(Node node)
    {
        allowedValueRangeNode = node;
    }

    public AllowedValueRange()
    {
        //TODO Test
        allowedValueRangeNode = new Node(ELEM_NAME);
    }

    ////////////////////////////////////////////////
    //	minimum
    ////////////////////////////////////////////////

    public AllowedValueRange(Number max, Number min, Number step)
    {
        //TODO Test
        allowedValueRangeNode = new Node(ELEM_NAME);
        if (max != null)
            setMaximum(max.toString());
        if (min != null)
            setMinimum(min.toString());
        if (step != null)
            setStep(step.toString());
    }

    public static boolean isAllowedValueRangeNode(Node node)
    {
        return ELEM_NAME.equals(node.getName());
    }

    public Node getAllowedValueRangeNode()
    {
        return allowedValueRangeNode;
    }

    ////////////////////////////////////////////////
    //	maximum
    ////////////////////////////////////////////////

    public String getMinimum()
    {
        return getAllowedValueRangeNode().getNodeValue(MINIMUM);
    }

    public void setMinimum(String value)
    {
        getAllowedValueRangeNode().setNode(MINIMUM, value);
    }

    public String getMaximum()
    {
        return getAllowedValueRangeNode().getNodeValue(MAXIMUM);
    }

    ////////////////////////////////////////////////
    //	width
    ////////////////////////////////////////////////

    public void setMaximum(String value)
    {
        getAllowedValueRangeNode().setNode(MAXIMUM, value);
    }

    public String getStep()
    {
        return getAllowedValueRangeNode().getNodeValue(STEP);
    }

    public void setStep(String value)
    {
        getAllowedValueRangeNode().setNode(STEP, value);
    }
}
