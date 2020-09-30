package com.cf.demo.service.sentinel.anno_test;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liweinan
 * @date 2020/9/30
 */
// 需要加么？
@Component
public class ExceptionUtil {
    public static String handleException(BlockException ex) {
        System.err.println("错误发生: " + ex.getClass().getCanonicalName());
        return "error";
    }
}
