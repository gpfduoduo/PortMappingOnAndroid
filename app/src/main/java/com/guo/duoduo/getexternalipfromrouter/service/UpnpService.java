package com.guo.duoduo.getexternalipfromrouter.service;


import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.DeviceChangeListener;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.guo.duoduo.getexternalipfromrouter.MyApplication;
import com.guo.duoduo.getexternalipfromrouter.R;
import com.guo.duoduo.getexternalipfromrouter.constant.UpnpConstant;
import com.guo.duoduo.getexternalipfromrouter.entity.MappingEntity;
import com.guo.duoduo.getexternalipfromrouter.sdk.UpnpCommand;


/**
 * Created by 郭攀峰 on 2015/9/12.
 */
public class UpnpService extends Service
{
    private static final String tag = UpnpService.class.getSimpleName();

    private UpnpThread thread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        thread.destroy();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        thread = new UpnpThread();
        thread.start();
    }

    private static class UpnpThread extends Thread
    {
        private ControlPoint controlPoint;

        @Override
        public void destroy()
        {
            controlPoint.stop();
        }

        @Override
        public void run()
        {
            controlPoint = new ControlPoint();
            controlPoint.start();
            Message msg = Message.obtain();
            msg.what = UpnpConstant.MSG.find_start;
            msg.obj = MyApplication.getInstance().getResources()
                    .getString(R.string.start_find_igd_device);
            MyApplication.getInstance().sendMessage(msg);

            controlPoint.addDeviceChangeListener(new DeviceChangeListener()
            {
                @Override
                public void deviceAdded(Device dev)
                {
                    Log.d(tag, " device is found = " + dev.getModelName() + "\r\n"
                        + " description url = " + dev.getLocation());

                    if (dev == null)
                        return;
                    String deviceType = dev.getDeviceType();
                    if (deviceType.equals(UpnpConstant.IGD))
                    {
                        MyApplication.curDevice = dev;

                        Message msg = Message.obtain();
                        msg.what = UpnpConstant.MSG.find_ok;
                        msg.obj = MyApplication.getInstance().getResources()
                                .getString(R.string.find_device)
                            + dev.getModelName();
                        MyApplication.getInstance().sendMessage(msg);

                        String externalIp = UpnpCommand.GetExternalIPAddress(dev);
                        Message ipMsg = Message.obtain();
                        ipMsg.what = UpnpConstant.MSG.find_ok;
                        ipMsg.obj = MyApplication.getInstance().getResources()
                                .getString(R.string.external_ip)
                            + externalIp;
                        MyApplication.getInstance().sendMessage(ipMsg);

                        MappingEntity entity = UpnpCommand.GetGenericPortMappingEntry(
                            dev, 0);
                        if (entity != null)
                        {
                            Message mappingMsg = Message.obtain();
                            mappingMsg.what = UpnpConstant.MSG.find_ok;
                            ipMsg.obj = entity.toString();
                            MyApplication.getInstance().sendMessage(ipMsg);
                        }
                        else
                        {
                            Message mappingMsg = Message.obtain();
                            mappingMsg.what = UpnpConstant.MSG.find_ok;
                            ipMsg.obj = MyApplication.getInstance().getResources()
                                    .getString(R.string.unknown_error);
                            MyApplication.getInstance().sendMessage(ipMsg);
                        }

                        Message endMsg = Message.obtain();
                        endMsg.what = UpnpConstant.MSG.find_end;
                        MyApplication.getInstance().sendMessage(endMsg);
                    }
                }

                @Override
                public void deviceRemoved(Device dev)
                {
                }
            });
        }

    }
}
