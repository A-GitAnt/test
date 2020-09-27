package com.cf.demo.service.S20;

import java.util.Arrays;

/**
 * @author liweinan
 * @date 2020/8/12
 *
 * https://www.cnblogs.com/hapjin/p/5818418.html
 */
public class PackageDemo2 {
    public static int getMaxValue(int[] weight, int[] value, int w, int n) {
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) { //i: 对于前i个物品
            for (int j = 1; j <= w; j++) {  //j: 背包容量
                if (weight[i] > j) {
                    //当前物品i的重量比背包容量j大，装不下，肯定就是不装
                    dp[i][j] = dp[i - 1][j];
                    // System.out.print(dp[i][j]+ " ");
                } else { //装得下，Max价值{不装物品i，装物品i }
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                    //System.out.print(dp[i][j]+ " ");
                }
            }
            // System.out.println();
        }

        int[] inPackageItem = getPosition(n, dp, weight);
        System.out.println(Arrays.toString(inPackageItem));
        return dp[n][w];
    }

    /* 返回最优重量方案的选择位置 */
    public static int[] getPosition(int num, int[][] dp, int[] weight) {
        int j = 20;
        int[] position = new int[num + 1];
        for (int i = num; i > 0; i--) {
            if (dp[i][j] > dp[i - 1][j]) {
                System.out.println(i + "  ");//输出选中的物品的编号
                position[i] = 1;
                j = j - weight[i];
                if (j < 0) {
                    break;
                }
            }
        }
        return position;
    }

    public static void main(String[] args) {

        int n = 5, w = 20;                    //物品个数，背包容量
        int[] value = {0, 6, 4, 5, 4, 6};     //各个物品的价值
        int[] weight = {0, 6, 4, 5, 4, 6};    //各个物品的重量
        System.out.println(getMaxValue(weight, value, w, n));
    }
}
