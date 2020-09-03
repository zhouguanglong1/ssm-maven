package com.zhougl.algo;

import lombok.Data;

import java.util.*;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/9/3 21:54
 */
public class DFSBFSTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        List<TreeNode> children = new ArrayList<>();
        treeNode.setChildren(children);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        children.add(treeNode1);
        children.add(treeNode2);
        List<TreeNode> children2 = new ArrayList<>();
        treeNode1.setChildren(children2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        children2.add(treeNode3);
        children2.add(treeNode4);

        TreeNode bfs = bfs(1, treeNode);

        dfs(bfs);
    }

    @Data
    private static class TreeNode {
        private int val;
        private List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static TreeNode bfs(int target, TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return null;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.println(treeNode.getVal());
            if (treeNode.getChildren() != null) {
                List<TreeNode> children = treeNode.getChildren();
                queue.addAll(children);
            }
            if (target == treeNode.getVal()) {
                return treeNode;
            }
        }
        return null;
    }

    private static void dfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return;
        }

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.println(pop.getVal());
            if (pop.getChildren() != null) {
                List<TreeNode> children = pop.getChildren();
                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }
        }

    }

}
