package com.guo.duoduo.getexternalipfromrouter.entity;


/**
 * Created by 郭攀峰 on 2015/9/13.
 */
public class MappingEntity
{
    public String NewRemoteHost;
    public String NewExternalPort;
    public String NewProtocol;
    public String NewInternalPort;
    public String NewInternalClient;
    public String NewEnabled;
    public String NewPortMappingDescription;
    public String NewLeaseDuration;

    @Override
    public String toString()
    {
        return "\r\n" + "NewRemoteHost='" + NewRemoteHost + "\r\n"
            + "NewExternalPort='" + NewExternalPort + "\r\n" + "NewProtocol='"
            + NewProtocol + "\r\n" + ", NewInternalPort='" + NewInternalPort + "\r\n"
            + "NewInternalClient='" + NewInternalClient + "\r\n" + "NewEnabled='"
            + NewEnabled + "\r\n" + "NewPortMappingDescription='"
            + NewPortMappingDescription + "\r\n" + "NewLeaseDuration='"
            + NewLeaseDuration + "\r\n";
    }
}
