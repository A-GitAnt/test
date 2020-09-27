package com.cf.demo.service.S20;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liweinan
 * @date 2020/8/12
 */
public class PackageDemo {
    public static int getMaxValue(int[] weight, int[] value, int w, int n) {
        int[][] table = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) { //i: 对于前i个物品
            for (int j = 1; j <= w; j++) {  //j: 背包容量
                if (weight[i] > j) {
                    //当前物品i的重量比背包容量j大，装不下，肯定就是不装
                    table[i][j] = table[i - 1][j];
                    // System.out.print(table[i][j]+ " ");
                } else { //装得下，Max价值{不装物品i，装物品i }
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - weight[i]] + value[i]);
                    //System.out.print(table[i][j]+ " ");
                }
            }
            // System.out.println();
        }
        return table[n][w];
    }

    public static void main(String[] args) {

        int n = 5, w = 10;                    //物品个数，背包容量
        int[] value = {0, 6, 3, 5, 4, 6};     //各个物品的价值
        int[] weight = {0, 2, 2, 6, 5, 4};    //各个物品的重量
        System.out.println(getMaxValue(weight,value,w,n));

    }
 }
