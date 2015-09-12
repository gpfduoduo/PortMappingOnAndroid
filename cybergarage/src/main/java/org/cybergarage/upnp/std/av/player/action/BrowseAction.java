/******************************************************************
 *
 * MediaPlayer for CyberLink
 *
 * Copyright (C) Satoshi Konno 2005
 *
 * File : BrowseAction.java
 *
 * 09/26/05 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.std.av.player.action;


import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;


public class BrowseAction
{
    ////////////////////////////////////////////////
    // Constants
    ////////////////////////////////////////////////

    public final static String OBJECT_ID = "ObjectID";
    public final static String BROWSE_FLAG = "BrowseFlag";
    public final static String FILTER = "Filter";
    public final static String STARTING_INDEX = "StartingIndex";
    public final static String REQUESTED_COUNT = "RequestedCount";
    public final static String SORT_CRITERIA = "SortCriteria";

    public final static String BROWSE_METADATA = "BrowseMetadata";
    public final static String BROWSE_DIRECT_CHILDREN = "BrowseDirectChildren";

    public final static String RESULT = "Result";
    public final static String NUMBER_RETURNED = "NumberReturned";
    public final static String TOTAL_MACHES = "TotalMatches";
    public final static String UPDATE_ID = "UpdateID";

    ////////////////////////////////////////////////
    // Member
    ////////////////////////////////////////////////

    private Action action;

    ////////////////////////////////////////////////
    // Constrictor
    ////////////////////////////////////////////////

    public BrowseAction(Action action)
    {
        this.action = action;
    }

    ////////////////////////////////////////////////
    // getArgument
    ////////////////////////////////////////////////

    public Argument getArgument(String name)
    {
        return action.getArgument(name);
    }

    ////////////////////////////////////////////////
    // Request
    ////////////////////////////////////////////////

    public String getBrowseFlag()
    {
        return action.getArgumentValue(BROWSE_FLAG);
    }

    public void setBrowseFlag(String browseFlag)
    {
        action.setArgumentValue(BROWSE_FLAG, browseFlag);
    }

    public boolean isMetadata()
    {
        return BROWSE_METADATA.equals(getBrowseFlag());
    }

    public boolean isDirectChildren()
    {
        return BROWSE_DIRECT_CHILDREN.equals(getBrowseFlag());
    }

    public String getObjectID()
    {
        return action.getArgumentValue(OBJECT_ID);
    }

    public void setObjectID(String objectID)
    {
        action.setArgumentValue(OBJECT_ID, objectID);
    }

    public int getStartingIndex()
    {
        return action.getArgumentIntegerValue(STARTING_INDEX);
    }

    public void setStartingIndex(int idx)
    {
        action.setArgumentValue(STARTING_INDEX, idx);
    }

    public int getRequestedCount()
    {
        return action.getArgumentIntegerValue(REQUESTED_COUNT);
    }

    public void setRequestedCount(int count)
    {
        action.setArgumentValue(REQUESTED_COUNT, count);
    }

    ////////////////////////////////////////////////
    // Request
    ////////////////////////////////////////////////

    public int getNumberReturned()
    {
        return action.getArgumentIntegerValue(NUMBER_RETURNED);
    }

    public void setNumberReturned(int value)
    {
        action.setArgumentValue(NUMBER_RETURNED, value);
    }

    public int getTotalMatches()
    {
        return action.getArgumentIntegerValue(TOTAL_MACHES);
    }

    public String getSortCriteria()
    {
        return action.getArgumentValue(SORT_CRITERIA);
    }

    public void setSortCriteria(String sortCaiteria)
    {
        action.setArgumentValue(SORT_CRITERIA, sortCaiteria);
    }

    public String getFilter()
    {
        return action.getArgumentValue(FILTER);
    }

    ////////////////////////////////////////////////
    // Result
    ////////////////////////////////////////////////

    public void setFilter(String filter)
    {
        action.setArgumentValue(FILTER, filter);
    }

    public void setResult(String value)
    {
        action.setArgumentValue(RESULT, value);
    }

    public void setTotalMaches(int value)
    {
        action.setArgumentValue(TOTAL_MACHES, value);
    }

    public void setUpdateID(int value)
    {
        action.setArgumentValue(UPDATE_ID, value);
    }

    ////////////////////////////////////////////////
    // post
    ////////////////////////////////////////////////

    public boolean postControlAction()
    {
        return action.postControlAction();
    }
}
