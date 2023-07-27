package com.idc.autotest.test.service;

import com.idc.autotest.test.url.CoralPathGenerator;
import com.idc.autotest.util.HttpRequestGetPostCommon;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 逻辑处理层
 */
public class CoralService {
    //测试一个get请求
    public static void case1(){
        URIBuilder uriBuilder=new URIBuilder().setScheme("http").setHost("hanyu.baidu.com")
                .setParameter("v","201703281500");
        URI uri=null;
        try {
            uri=uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //响应结果
        String result = HttpRequestGetPostCommon.doGet(uri.toString());//get请求

    }

    //测试一个post请求
    public static void case2(String param)throws Exception{
        URIBuilder uriBuilder=new URIBuilder().setScheme("http").setHost("collect-v6.51.la")
                .setPath(CoralPathGenerator.langdetect());
        URI uri=null;
        try {
            uri=uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //响应结果
        String result = HttpRequestGetPostCommon.doPost(uri.toString(),param);//get请求

    }

}
