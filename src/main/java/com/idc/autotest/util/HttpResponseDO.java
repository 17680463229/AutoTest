package com.idc.autotest.util;

import org.apache.http.Header;
import org.apache.http.StatusLine;

import java.util.HashMap;

/**
 * 每次请求接口后用该对象接收返回值
 */
public class HttpResponseDO {
    String contentBody;
    StatusLine status;
    HashMap<String, Header> header;

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    public StatusLine getStatus() {
        return status;
    }

    public void setStatus(StatusLine status) {
        this.status = status;
    }

    public HashMap<String, Header> getHeader() {
        return header;
    }

    public void setHeader(HashMap<String, Header> header) {
        this.header = header;
    }
}

