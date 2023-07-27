package com.idc.autotest.util;

/*
把所有的ip获取到赋值，然后调用的时候不用每次读取properties文件
URIBuilder+IdcIpConstants（提供ip和端口）+CoralPathGenerator（提供请求接口）进行拼接
 */
public class IdcIpConstants {
    public String pantherhost = "panther.alibaba.net";   //panther域名
    public Integer port = 7001;//端口号
    public String qidianDaily = "qidian.alibaba.net";//qidian 域名
    public String workId = "WB423044";//工号

    public IdcIpConstants(String qidianDaily, Integer port,String pantherhost, String workId)  {
        this.port = port;
        this.pantherhost = pantherhost;
        this.workId = workId;
        this.qidianDaily=qidianDaily;
    }
}
