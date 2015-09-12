# GetExternalIpFromRouter
通过upnp 可以进行端口的动态映射，获取路由器的外部wan口ip地址。  
假设内网某台机器连接的网关设备支持 UPNP igd 接口并开启了此项功能，那么网关设备就能够响应内网机器的请求，执行一些和网关相关的操作，比如将内网机器的某个端口映射到外网某个固定端口（即 NAT）。这样就能够使外网能够直接访问到内网机器的某端口。

## 项目介绍
路由器中有一个配置，开启upnp，默认是打开的，这样通过upnp 就可以访问 路由器的IGW，它就会返回外网的ip地址。

## 正在进行中......


## 基本原理解析


## 效果图
![效果图](https://github.com/gpfduoduo/GetExternalIpFromRouter/blob/master/portmap.gif)
