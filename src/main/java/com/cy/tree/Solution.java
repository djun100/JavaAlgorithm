package com.cy.tree;

/**
 Definition for binary tree
 */
public class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class ReturnType {
        int maxSingle;
        int max;
        ReturnType (int maxSingle, int max) {
            this.max = max;
            this.maxSingle = maxSingle;
        }
    }

    public int maxPathSum(TreeNode root) {
        return dfs(root).max;
    }
/////////////////////////////////////////////////////////////////////////////////start
    public ReturnType dfs(TreeNode root) {
        ReturnType ret = new ReturnType(Integer.MIN_VALUE, Integer.MIN_VALUE);
        if (root == null) {
            return ret;
        }

        ReturnType left = dfs(root.left);
        ReturnType right = dfs(root.right);

        int cross = root.val;

        // if any of the path of left and right is below 0, don't add it.
        cross += Math.max(0, left.maxSingle);
        cross += Math.max(0, right.maxSingle);

        // 注意，这里不可以把Math.max(left.maxSingle, right.maxSingle) 与root.val加起来，
        // 会有可能越界！
        int maxSingle = Math.max(left.maxSingle, right.maxSingle);

        // may left.maxSingle and right.maxSingle are below 0
        maxSingle = Math.max(maxSingle, 0);
        maxSingle += root.val;

        ret.maxSingle = maxSingle;
        ret.max = Math.max(right.max, left.max);
        ret.max = Math.max(ret.max, cross);

        return ret;
    }
    /////////////////////////////////////////////////////////////////////////////////end

    public static void main(String[] args) {
        TreeNode node0 =new TreeNode(0);
        TreeNode node8 =new TreeNode(8);
        TreeNode node1 =new TreeNode(1);
        TreeNode node2 =new TreeNode(2);
        TreeNode node3 =new TreeNode(3);
        TreeNode node9 =new TreeNode(9);
        TreeNode node10 =new TreeNode(10);
        node0.left=node8;
        node0.right=node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node9;
        node2.right = node10;

        System.out.println(new Solution().maxPathSum(node0));
    }
}