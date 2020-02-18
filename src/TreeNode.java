import java.util.LinkedList;
import java.util.Queue;

// leetcode tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    // leetcode tree node deserialize
    public static TreeNode deserialize(String data) {
        data = preprocess(data);
        String[] a = data.split(",");
        if (a[0].equals("null")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(a[0]));
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int i = 1;
        while (i < a.length) {
            TreeNode curr = queue.poll();
            if (!a[i].equals("null")) {
                curr.left = new TreeNode(Integer.valueOf(a[i]));
                queue.offer(curr.left);
            }
            i++;
            if (!a[i].equals("null")) {
                curr.right = new TreeNode(Integer.valueOf(a[i]));
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    // leetcode preprocess
    private static String preprocess(String str) {
        int s = 0, e = str.length();
        if (str.charAt(s) == '[') s++;
        if (str.charAt(e-1) == ']') e--;
        return str.substring(s, e);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String data = "[3,5,1,6,2,0,8,null,null,7,4]";
        System.out.println(TreeNode.deserialize(data).left.right.right.val);
    }
}