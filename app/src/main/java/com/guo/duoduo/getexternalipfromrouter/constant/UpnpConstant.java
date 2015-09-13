package com.guo.duoduo.getexternalipfromrouter.constant;


/**
 * Created by 郭攀峰 on 2015/9/12.
 */
public class UpnpConstant
{
    public static final String IGD = "urn:schemas-upnp-org:device:InternetGatewayDevice:1";

    public interface SERVICE_TYPE
    {
        public static final String WANIPConnection = "urn:schemas-upnp-org:service:WANIPConnection:1";
    }

    public interface ACTION
    {
        /**
         * 添加端口映射
         */
        public static final String AddPortMapping = "AddPortMapping";
        /**
         * 删除端口映射
         */
        public static final String DeletePortMapping = "DeletePortMapping";
        /**
         * 获取外网ip地址
         */
        public static final String GetExternalIPAddress = "GetExternalIPAddress";
        /**
         * 获取通用的端口映射表
         */
        public static final String GetGenericPortMappingEntry = "GetGenericPortMappingEntry";

    }

    public static final String NewExternalIPAddress = "NewExternalIPAddress";

    public interface MSG
    {
        public static final int find_fail = -1;
        public static final int find_ok = 0;
        public static final int find_start = 2;
        public static final int find_end = 1;

        public static final int add_port_ok = 3;
        public static final int add_port_fail = 4;
    }
}
