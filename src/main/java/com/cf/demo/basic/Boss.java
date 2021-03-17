package com.cf.demo.basic;

/**
 * @author liweinan
 * @date 2020/11/20
 */
public class Boss extends Employee {

    public Boss(String name, Integer age) {
        super(name, age);
    }


    @Override
    public String getName() {
        return "Boss: " + super.getName();
    }
}
