package com.cf.demo.service.time.test;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class IntegralInfoVO implements Serializable {
    private Long uid;

    private String integralType;

    private BigDecimal goldInGram;
}
