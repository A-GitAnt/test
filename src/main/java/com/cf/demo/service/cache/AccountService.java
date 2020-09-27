package com.cf.demo.service.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author liweinan
 * @date 2020/7/14
 */
@Slf4j
@Service
public class AccountService {

    @Cacheable("accountCache")
    public Account getAccountByName(String name) {
        log.info("getAccountByName: {}", name);
        return getAccountFromDB(name);
    }

    public Account getAccountFromDB(String name) {
        log.info("getAccountByDB: {}", name);
        return new Account(name);
    }

    @CachePut(value = "accountCache", key = "#name")
    public void save(String name) {
        log.info("save: {}", name);
    }

    @CacheEvict(value = "accountCache", key = "#account.getName()")
    public void updateAccount(Account account) {
        updateDB(account);
    }

    // 清空 accountCache 缓存
    @CacheEvict(value="accountCache", allEntries=true)
    public void reload() {
    }

    private void updateDB(Account account) {
        System.out.println("real update db..."+account.getName());
    }

}
