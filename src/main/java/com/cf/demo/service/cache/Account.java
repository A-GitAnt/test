package com.cf.demo.service.cache;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liweinan
 * @date 2020/7/14
 */
@Data
public class Account {
    private int id;
    private String name;

    public Account(String name) {
        this.name = name;
    }
}

