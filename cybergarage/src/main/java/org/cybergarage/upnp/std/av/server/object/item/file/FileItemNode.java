/******************************************************************
 *
 * MediaServer for CyberLink
 *
 * Copyright (C) Satoshi Konno 2003
 *
 * File : FileItemNode.java
 *
 * Revision:
 *
 * 02/12/04 - first revision.
 *
 ******************************************************************/

package org.cybergarage.upnp.std.av.server.object.item.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.cybergarage.upnp.std.av.server.ContentDirectory;
import org.cybergarage.upnp.std.av.server.object.Format;
import org.cybergarage.upnp.std.av.server.object.item.ItemNode;
import org.cybergarage.util.Debug;
import org.cybergarage.util.FileUtil;


public class FileItemNode extends ItemNode
{
    ////////////////////////////////////////////////
    // Constroctor
    ////////////////////////////////////////////////

    private File itemFile;

    ////////////////////////////////////////////////
    // File/TimeStamp
    ////////////////////////////////////////////////

    public FileItemNode()
    {
        setFile(null);
    }

    public File getFile()
    {
        return itemFile;
    }

    public void setFile(File file)
    {
        itemFile = file;
    }

    public long getFileTimeStamp()
    {
        long itemFileTimeStamp = 0;
        if (itemFile != null)
        {
            try
            {
                itemFileTimeStamp = itemFile.lastModified();
            }
            catch (Exception e)
            {
                Debug.warning(e);
            }
        }
        return itemFileTimeStamp;
    }

    public boolean equals(File file)
    {
        if (itemFile == null)
            return false;
        return itemFile.equals(file);
    }

    ////////////////////////////////////////////////
    // Abstract methods
    ////////////////////////////////////////////////

    public byte[] getContent()
    {
        byte fileByte[] = new byte[0];
        try
        {
            fileByte = FileUtil.load(itemFile);
        }
        catch (Exception e)
        {
        }
        return fileByte;
    }

    public long getContentLength()
    {
        return itemFile.length();
    }

    public InputStream getContentInputStream()
    {
        try
        {
            return new FileInputStream(itemFile);
        }
        catch (Exception e)
        {
            Debug.warning(e);
        }
        return null;
    }

    public String getMimeType()
    {
        ContentDirectory cdir = getContentDirectory();
        File itemFile = getFile();
        Format itemFormat = cdir.getFormat(itemFile);
        if (itemFormat == null)
        {
            return "*/*";
        }
        return itemFormat.getMimeType();
    }
}
