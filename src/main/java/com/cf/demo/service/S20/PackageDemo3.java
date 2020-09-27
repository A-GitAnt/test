package com.cf.demo.service.S20;

import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author liweinan
 * @date 2020/8/12
 *
 * https://www.cnblogs.com/hapjin/p/5818418.html
 */
public class PackageDemo3 {

    /**
     * 根据当前的打包状态计算总价
     * @param weight 重量数组
     * @return
     */
    public static int getTotalPrice(int[] weight, int[] packInfo, int packageSize) {
        List<Integer> exPackageList = new LinkedList<>();
        int inPackageSum = 0;
        for (int i = 1; i < weight.length; i++) {
            if (packInfo[i] == 1) {
                inPackageSum += weight[i];
            } else {
                exPackageList.add(weight[i]);
            }

        }
        int inPackage = getPackagePrice(inPackageSum, packageSize);
        int exPackage = getPackagePriceArray(exPackageList, packageSize);
        return inPackage + exPackage;
    }

    public static int getPackagePrice(int weight) {
        if (weight == 0) {
            return 0;
        }
        if (weight < 5) {
            return weight * 2 + 5;
        } else {
            return weight * 2 + 7;
        }
    }

    public static int getPackagePrice(int weight, int capacity) {
        if (weight == 0) {
            return 0;
        }
        if (capacity == 5) {
            return weight * 2 + 5;
        } else if (capacity == 8) {
            return weight * 2 + 7;
        }
        else {
            return weight * 2 + 7;
        }
    }


    public static PackageCostResult getPackagePriceResult(int weight) {
        if (weight == 0) {
            return new PackageCostResult(0,0);
        }
        if (weight <= 5) {
            return new PackageCostResult(5, weight * 2 + 5);
        } else {
            return new PackageCostResult(8, weight * 2 + 7);
        }
    }


    public static int getPackagePriceArray(Collection<Integer> weight, int capacity) {
        int sum = 0;
        for (int i : weight) {
            sum += getPackagePrice(i, capacity);
        }
        return sum;
    }



    public static StepSelectionResult getBestSelection(int[] weight, int capacity, int n) {
        /**
         * dp[i][j] 背包容量为j的情况下，前i个物品的最佳放入方法。【最佳代表当前合并方法对应的总运费是最低的】
         */
        int bestCost = 0;
        int[][][] dp = new int[n + 1][capacity + 1][n + 1];
        for (int i = 1; i <= n; i++) { //i: 对于前i个物品
            for (int j = 1; j <= capacity; j++) {  //j: 背包容量
                if (weight[i] > j) {
                    //当前物品i的重量比背包容量j大，装不下，肯定就是不装
                    dp[i][j] = dp[i - 1][j];
                } else { //装得下，Max价值{不装物品i，装物品i }

                    /* 不装该物品，总邮费 */
                    int exPackageTotalPrice = getTotalPrice(weight, dp[i - 1][j], capacity);

                    /* 装该物品，总邮费 */
                    int[] bestSelectionBefore = dp[i - 1][j - weight[i]];
                    int[] tempPackInfo = Arrays.copyOf(bestSelectionBefore, bestSelectionBefore.length);
                    tempPackInfo[i] = 1;
                    int inPackageTotalPrice = getTotalPrice(weight, tempPackInfo, capacity);
                    if (exPackageTotalPrice < inPackageTotalPrice) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = tempPackInfo;
                    }

                    if (i == n && j == capacity) {
                        bestCost = Math.min(exPackageTotalPrice, inPackageTotalPrice);
                    }
                }
            }
        }
        return new StepSelectionResult(bestCost ,dp[n][capacity], capacity);
    }

    public static StepSelectionResult getBestSelectionByStep(int[] weight, int[] capacity, int num) {
        StepSelectionResult best = null;
        int min = Integer.MAX_VALUE;
        for (int c : capacity) {
            StepSelectionResult result = getBestSelection(weight, c, num);
            min = Math.min(result.getTotalCost(), min);
            if (result.getTotalCost() == min) {
                best = result;
            }
        }
        return best;
    }

    public static AllSelectionResult getBestSelection(int[] weight, int[] capacity, int num) {
        int notSelectedNum = num;
        Map</* capacity*/Integer, /* [[1,2,3], [4,5]] */List<List<Integer>>> map = new HashMap<>();
        int totalCost = 0;
        int[] w = Arrays.copyOf(weight, weight.length);
        while (notSelectedNum > 0) {
            /* 核心 */
            StepSelectionResult bestSelectionByStep = getBestSelectionByStep(w, capacity, w.length - 1);
            int capacitySelect = bestSelectionByStep.getCapacity();
            int[] selection = bestSelectionByStep.getSelection();
            totalCost += getBestCost(w, selection, capacitySelect);
            List<List<Integer>> allLists = map.get(capacitySelect);
            if (CollectionUtils.isEmpty(allLists)) {
                allLists = Lists.newArrayList();
            }
            List<Integer> selectedList = getSelectedWeight(w, selection);
            allLists.add(selectedList);
            map.put(capacitySelect, allLists);
            w = removeSelectedWeight(w, selection);
            notSelectedNum -= selectedList.size();
        }

        return new AllSelectionResult(totalCost, map);
    }

    public static int getSumOfZero(int[] selection) {
        int sum = 0;
        for (int i = 0; i < selection.length; i++) {
            if (selection[i] == 0) {
                sum += 1;
            }
        }
        return sum;
    }

    /* todo: 有更好的实现吗？*/
    private static List<Integer> getIndex(int[] selection) {
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < selection.length; i++) {
            if (selection[i] == 1) {
                list.add(i);
            }
        }
        return list;
    }

    /* todo: 有更好的实现吗？*/
    private static List<Integer> getSelectedWeight(int[] w, int[] selection) {
        List<Integer> list = Lists.newArrayList();
        if (w.length != selection.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < w.length; i++) {
            if (selection[i] == 1) {
                list.add(w[i]);
            }
        }
        return list;
    }


    /* todo: 有更好的实现吗？*/
    private static int[] removeSelectedWeight(int[] w, int[] selection) {
        int[] list = new int[getSumOfZero(selection)];
        int k = 1;
        list[0] = 0;
        for (int i = 1; i < w.length; i++) {
            if (selection[i] == 0) {
                list[k++] = w[i];
            }
        }
        return list;
    }


    public static int getBestCost(int[] weight, int[] selection, int capacity) {
        int sum = 0;
        if (weight.length != selection.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < selection.length; i++) {
            if (selection[i] == 1) {
                sum += weight[i];
            }
        }
        return getPackagePrice(sum, capacity);
    }

    public static void main(String[] args) {

        int capacity = 5;                    //物品个数，背包容量
        int[] weight = {0, 2, 2, 3, 4};    //各个物品的重量
        int num = weight.length - 1;

        // System.out.println(Arrays.toString(getBestSelection(weight, capacity, num).getSelection()));
        // System.out.println(getBestSelection(weight, capacity, num).getTotalCost());
        //
        // System.out.println("");
        // System.out.println(Arrays.toString(getBestSelection(weight, capacity + 3, num).getSelection()));
        // System.out.println(getBestSelection(weight, capacity + 3, num).getTotalCost());

        AllSelectionResult bestSelection = getBestSelection(weight, new int[]{5,8}, num);
        System.out.println(bestSelection);
    }
}
