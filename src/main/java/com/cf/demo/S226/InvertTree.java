package com.cf.demo.S226;

import javax.swing.tree.TreeNode;
import java.util.List;

/**
 * @author liweinan
 * @date 2020/8/13
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }


        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;


        invertTree(root.left);

        invertTree(root.right);
        return root;
    }


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    static void f(Integer n) {
        n += 1;
    }

    public static void main(String[] args) {
        Integer x = 0;
        f(x);
        System.out.println(x);
    }

}

