package com.cf.demo.service.S20;

import java.util.*;

/**
 * 有效的括号
 *
 * @author liweinan
 * @date 2020/8/12
 */
public class S20 {
    public static boolean isValid(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "")
                    .replace("[]", "")
                    .replace("()", "");
        }
        return s.isEmpty();
    }

    public static final Map<Character, Character> map = new HashMap<>();
    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        map.put('#', '#');
    }


    public static boolean isValid2(String s) {
        Stack<Character> stackDemo = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stackDemo.push(c);
            } else {
                char pop = stackDemo.isEmpty() ? '#' : stackDemo.pop();
                if (c != map.get(pop)) {
                    return false;
                }
            }
        }
        return stackDemo.isEmpty();
    }

    public static boolean isValid3(String s) {
        Stack<Character> stackDemo = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stackDemo.push(')');
            } else if (c == '[') {
                stackDemo.push(']');
            } else if (c == '{') {
                stackDemo.push('}');
            } else if (stackDemo.isEmpty() || stackDemo.pop() != c) {
                return false;
            }
        }
        return stackDemo.isEmpty();
    }


    public static void main(String[] args) {
        boolean valid = isValid3("()[]{}");
        System.out.println(valid);
    }
}
