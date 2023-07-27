package com.idc.autotest.test;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;
import com.idc.autotest.test.service.CoralService;

public class testcase {
    @Test
    public void case1(){
        CoralService.case1();
    }

    @Test
    public void case2()throws Exception{
        JSONObject param=new JSONObject();
        param.put("dt","4");
        CoralService.case2(param.toJSONString());
    }
}
