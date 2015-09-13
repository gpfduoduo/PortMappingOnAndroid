# GetExternalIpFromRouter
通过upnp 可以进行端口的动态映射，获取路由器的外部wan口ip地址。  
假设内网某台机器连接的网关设备支持 UPNP igd 接口并开启了此项功能，那么网关设备就能够响应内网机器的请求，执行一些和网关相关的操作，比如将内网机器的某个端口映射到外网某个固定端口（即 NAT）。这样就能够使外网能够直接访问到内网机器的某端口。

## 项目介绍
路由器中有一个配置，开启upnp，默认是打开的，这样通过upnp soap协议就可以访问 路由器的IGW，它就会返回外网的ip地址，可以获取当前已经实现的端口映射相关信息，同理也可以实现端口的映射等功能。

## 基本原理解析
1、通过upnp发现你的路由器设备，具体的deviceType="urn:schemas-upnp-org:device:InternetGatewayDevice:1"  
2、获取设备后，解析设备描述信息，并获取服务：urn:schemas-upnp-org:service:WANIPConnection:1  
3、解析服务（urn:schemas-upnp-org:service:WANIPConnection:1）的描述信息（scpdurl地址），获取该服务中的所有接口  
4、通过saop向接口post消息，参数按照上一步获取的接口信息进行设置，就可以返回相应的信息。  

下面列举几个接口  
增加端口映射。   
```
"AddPortMapping"  
"<NewRemoteHost></NewRemoteHost>rn"  "<NewExternalPort>ExternalPort</NewExternalPort>rn"                               "<NewProtocol>Protocol</NewProtocol>rn"                                      "<NewInternalPort>InternalPort</NewInternalPort>n"
"<NewInternalClient>InternalClient</NewInternalClient>rn"                 
"<NewEnabled>1</NewEnabled>rn"
"<NewPortMappingDescription>PortMappingDescription"       "</NewPortMappingDescription>rn"       
"<NewLeaseDuration>LeaseDuration</NewLeaseDuration>rn" 
```
删除端口映射　  
```
"DeletePortMapping"   
"<NewRemoteHost></NewRemoteHost>rn" "<NewExternalPort>ExternalPort</NewExternalPort>rn"   "<NewProtocol>Protocol</NewProtocol>rn" 
```
获得端口映射信息　  
```
"GetGenericPortMappingEntry"   
"<NewPortMappingIndex>PortMappingIndex</NewPortMappingIndex>"  "<NewRemoteHost></NewRemoteHost>rn"   "<NewExternalPort></NewExternalPort>rn" "<NewProtocol></NewProtocol>rn"     "<NewInternalPort></NewInternalPort>rn" "<NewInternalClient></NewInternalClient>rn" "<NewEnabled>1</NewEnabled>rn"  "<NewPortMappingDescription>"                                          "</NewPortMappingDescription>rn"           　"<NewLeaseDuration></NewLeaseDuration>rn"
```
其中斜体部分需要在编程是填入的。ExternalPort 外部端口。InternalPort内部端口。这两者一般就填映射的端口。Protocal 填TCP或UDP。InterClient 一般就是本地IP地址。PortMappingDescription 填写端口映射的描述，比如什么程序建立了这个端口。LeaseDuration 是映射的持续时间，用0表示不永久。PortMappingIndex 是端口映射索引，路由上第几个映射。


## 效果图
![效果图](https://github.com/gpfduoduo/GetExternalIpFromRouter/blob/master/portmap.gif)
