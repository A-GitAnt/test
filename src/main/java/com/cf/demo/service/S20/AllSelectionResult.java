package com.cf.demo.service.S20;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author liweinan
 * @date 2020/7/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllSelectionResult {
    private int totalCost;

    private Map</* capacity*/Integer, /* [[1,2,3], [4,5]] */List<List<Integer>>> map;
}

