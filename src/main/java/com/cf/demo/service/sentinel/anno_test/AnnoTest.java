package com.cf.demo.service.sentinel.anno_test;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author liweinan
 * @date 2020/9/30
 */
@Service
public class AnnoTest {

    @SentinelResource(value = "get", blockHandler = "exceptionHandler")
    public String get(String id) {
        System.err.println(": " + id);

        return "http://cxytiandi.com";
    }

    public String exceptionHandler(String id, BlockException e) {
        e.printStackTrace();
        System.err.println("错误发生: " + e.getClass().getCanonicalName());
        return "错误发生在" + id;
    }


    @SentinelResource(value = "get2", blockHandler = "handleException", blockHandlerClass = { ExceptionUtil.class })
    public String get2(String id) {
        System.err.println(": " + id);

        return "http://cxytiandi.com";
    }
}
