package leetcode.quickTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class IterativeTreeTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.value);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.value);
            curr = curr.right;
        }
        return result;
    }

    public static void main(String[] args) {
        IterativeTreeTraversal sol = new IterativeTreeTraversal();
        TreeNode n4 = new TreeNode(4, null, null);
        TreeNode n5 = new TreeNode(5, null, null);
        TreeNode n6 = new TreeNode(6, null, null);
        TreeNode n7 = new TreeNode(7, null, null);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n3 = new TreeNode(3, n6, n7);
        TreeNode n1 = new TreeNode(1, n2, n3);
        System.out.println("preorder: " + sol.preorderTraversal(n1));
        System.out.println("inorder: " + sol.inorderTraversal(n1));
    }
}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}