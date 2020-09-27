package com.cf.demo.service.S20;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liweinan
 * @date 2020/7/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepSelectionResult {
    private int totalCost;
    private int[] selection;
    private int capacity;
}

