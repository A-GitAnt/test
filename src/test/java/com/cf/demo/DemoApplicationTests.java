package com.cf.demo;

import com.cf.demo.service.cache.Account;
import com.cf.demo.service.cache.AccountService;
import com.cf.demo.service.sentinel.anno_test.AnnoTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

    @Autowired
    private AccountService accountService;

    @Resource
    private AnnoTest annoTest;

    @Test
    public void contextLoads() {
        Account abcv = accountService.getAccountByName("abcv");
        log.info( "contextLoads:{}", abcv.getName());

        /* 注意：第二次 getAccountByName 的入口日志都没有打印，说明就没执行该方法，直接走spring 的缓存 */
        abcv = accountService.getAccountByName("abcv");
        log.info( "contextLoads2:{}", abcv.getName());


        System.out.println("start testing clear cache...");    // 更新某个记录的缓存，首先构造两个账号记录，然后记录到缓存中
        Account account1 = accountService.getAccountByName("somebody1");
        Account account2 = accountService.getAccountByName("somebody2");
        // 开始更新其中一个
        account1.setId(1212);
        accountService.updateAccount(account1);
        accountService.getAccountByName("somebody1");// 因为被更新了，所以会查询数据库
        accountService.getAccountByName("somebody2");// 没有更新过，应该走缓存
        accountService.getAccountByName("somebody1");// 再次查询，应该走缓存

        // 更新所有缓存
        accountService.reload();
        accountService.getAccountByName("somebody1");// 应该会查询数据库
        accountService.getAccountByName("somebody2");// 应该会查询数据库
        accountService.getAccountByName("somebody1");// 应该走缓存
        accountService.getAccountByName("somebody2");// 应该走缓存

    }

    @Test
    public void test() throws InterruptedException {
        int i = 0;
        while (true) {
            annoTest.get2(String.valueOf(i++));
            Thread.sleep(100);
        }
    }

}
