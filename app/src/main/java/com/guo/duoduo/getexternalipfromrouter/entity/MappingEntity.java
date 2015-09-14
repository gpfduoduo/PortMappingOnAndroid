package com.guo.duoduo.getexternalipfromrouter.entity;


import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by 郭攀峰 on 2015/9/13.
 */
public class MappingEntity implements Parcelable
{
    public MappingEntity()
    {
    }

    public String NewRemoteHost = "";
    public String NewExternalPort = "";
    public String NewProtocol = "";
    public String NewInternalPort = "";
    public String NewInternalClient = "";
    public String NewEnabled = "";
    public String NewPortMappingDescription = "";
    public String NewLeaseDuration = "";

    @Override
    public String toString()
    {
        return "\r\n" + "NewRemoteHost=" + NewRemoteHost + "\r\n" + "NewExternalPort="
            + NewExternalPort + "\r\n" + "NewProtocol=" + NewProtocol + "\r\n"
            + "NewInternalPort=" + NewInternalPort + "\r\n" + "NewInternalClient="
            + NewInternalClient + "\r\n" + "NewEnabled=" + NewEnabled + "\r\n"
            + "NewPortMappingDescription=" + NewPortMappingDescription + "\r\n"
            + "NewLeaseDuration=" + NewLeaseDuration + "\r\n";
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.NewRemoteHost);
        dest.writeString(this.NewExternalPort);
        dest.writeString(this.NewProtocol);
        dest.writeString(this.NewInternalPort);
        dest.writeString(this.NewInternalClient);
        dest.writeString(this.NewEnabled);
        dest.writeString(this.NewPortMappingDescription);
        dest.writeString(this.NewLeaseDuration);
    }

    protected MappingEntity(Parcel in)
    {
        this.NewRemoteHost = in.readString();
        this.NewExternalPort = in.readString();
        this.NewProtocol = in.readString();
        this.NewInternalPort = in.readString();
        this.NewInternalClient = in.readString();
        this.NewEnabled = in.readString();
        this.NewPortMappingDescription = in.readString();
        this.NewLeaseDuration = in.readString();
    }

    public static final Creator<MappingEntity> CREATOR = new Creator<MappingEntity>()
    {
        public MappingEntity createFromParcel(Parcel source)
        {
            return new MappingEntity(source);
        }

        public MappingEntity[] newArray(int size)
        {
            return new MappingEntity[size];
        }
    };
}
