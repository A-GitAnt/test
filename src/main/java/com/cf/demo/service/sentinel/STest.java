package com.cf.demo.service.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liweinan
 * @date 2020/9/30
 */
public class STest {

    public static void main(String[] args) throws InterruptedException {
        // 配置规则.
        initFlowRules();

        while (true) {
            Entry entry = null;
            try {
                Thread.sleep(4);


                entry = SphU.entry("HelloWorld");
                // 被保护的逻辑
                System.out.println("hello world");


            } catch (BlockException ex) {
                // 处理被流控的逻辑
                System.out.println("XXXXXXX blocked! XXXXXX");
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(200);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    // https://www.cnblogs.com/yinjihuan/p/10516045.html
    // sentinel 限流
}
