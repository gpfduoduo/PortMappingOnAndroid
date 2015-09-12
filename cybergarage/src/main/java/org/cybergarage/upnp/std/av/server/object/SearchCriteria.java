/******************************************************************
 *
 * MediaServer for CyberLink
 *
 * Copyright (C) Satoshi Konno 2003-2004
 *
 * File : SearchCriteria.java
 *
 * Revision:
 *
 * 08/16/04 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.std.av.server.object;


public class SearchCriteria
{
    ////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////

    public final static String ID = "@id";
    public final static String PARENT_ID = "@parentID";
    public final static String TITLE = "dc:title";
    public final static String CREATOR = "dc:creator";
    public final static String CLASS = "upnp:class";
    public final static String DATE = "dc:date";

    public final static String AND = "and";
    public final static String OR = "or";

    public final static String EQ = "=";
    public final static String NEQ = "!=";
    public final static String LT = "<";
    public final static String LE = "<=";
    public final static String GT = ">";
    public final static String GE = ">=";

    public final static String CONTAINS = "contains";
    public final static String DOESNOTCONTAIN = "doesNotContain";
    public final static String DERIVEDFROM = "derivedfrom";
    public final static String EXISTS = "exists";

    public final static String TRUE = "true";
    public final static String FALSE = "false";

    public final static String WCHARS = " \t\n\f\r"; // \v couldn't be added.

    ////////////////////////////////////////////////
    // Constroctor
    ////////////////////////////////////////////////
    private String property;
    private String operation;

    ////////////////////////////////////////////////
    // property
    ////////////////////////////////////////////////
    private String value;
    private String logic;
    private boolean result;

    ////////////////////////////////////////////////
    // property
    ////////////////////////////////////////////////

    public SearchCriteria()
    {
        setProperty("");
        setOperation("");
        setValue("");
        setLogic("");
    }

    public SearchCriteria(SearchCriteria searchCri)
    {
        setProperty(searchCri.getProperty());
        setOperation(searchCri.getOperation());
        setValue(searchCri.getValue());
        setLogic(searchCri.getLogic());
        setResult(searchCri.getResult());
    }

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String val)
    {
        property = val;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String val)
    {
        operation = val;
    }

    public boolean isEQ()
    {
        return (operation.compareTo(EQ) == 0) ? true : false;
    }

    public boolean isNEQ()
    {
        return (operation.compareTo(NEQ) == 0) ? true : false;
    }

    public boolean isLT()
    {
        return (operation.compareTo(LT) == 0) ? true : false;
    }

    public boolean isLE()
    {
        return (operation.compareTo(LE) == 0) ? true : false;
    }

    public boolean isGT()
    {
        return (operation.compareTo(GT) == 0) ? true : false;
    }

    public boolean isGE()
    {
        return (operation.compareTo(GE) == 0) ? true : false;
    }

    public boolean isContains()
    {
        return (operation.compareTo(CONTAINS) == 0) ? true : false;
    }

    ////////////////////////////////////////////////
    // value
    ////////////////////////////////////////////////

    public boolean isDoesNotContain()
    {
        return (operation.compareTo(DOESNOTCONTAIN) == 0) ? true : false;
    }

    public boolean isDerivedFrom()
    {
        return (operation.compareTo(DERIVEDFROM) == 0) ? true : false;
    }

    public boolean isExists()
    {
        return (operation.compareTo(EXISTS) == 0) ? true : false;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String val)
    {
        value = val;
    }

    ////////////////////////////////////////////////
    // Logic
    ////////////////////////////////////////////////

    boolean isTrueValue()
    {
        return (value.compareTo(TRUE) == 0) ? true : false;
    }

    boolean isFalseValue()
    {
        return (value.compareTo(FALSE) == 0) ? true : false;
    }

    public String getLogic()
    {
        return logic;
    }

    public void setLogic(String val)
    {
        logic = val;
    }

    public boolean isLogicalAND()
    {
        return (logic.compareTo(AND) == 0) ? true : false;
    }

    ////////////////////////////////////////////////
    // Result
    ////////////////////////////////////////////////

    public boolean isLogicalOR()
    {
        return (logic.compareTo(OR) == 0) ? true : false;
    }

    public boolean getResult()
    {
        return result;
    }

    public void setResult(boolean value)
    {
        result = value;
    }
}
