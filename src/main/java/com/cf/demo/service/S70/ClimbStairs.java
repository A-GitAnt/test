package com.cf.demo.service.S70;

import java.util.List;

/**
 * @author liweinan
 * @date 2020/8/13
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static List<String> generateParenthesis(int n) {
        generateParenthesis(0, 0, n, "");
        return null;
    }

    public static void generateParenthesis(int left, int right, int max, String s) {
        if (left == max && right == max) {
            System.out.println(s);
            return;
        }

        if (left < max) {
            generateParenthesis(left + 1, right, max, s + "(");
        }

        if (left > right ) {
            generateParenthesis(left, right + 1, max, s + ")");
        }
    }

    public static void main(String[] args) {
        generateParenthesis(3);
    }
}

