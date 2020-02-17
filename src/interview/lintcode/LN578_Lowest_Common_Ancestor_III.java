package interview.lintcode;

class LN578_Lowest_Common_Ancestor_III {
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        Result res = helper(root, A, B);
        if (res.existA && res.existB) return res.lca;
        else return null;
    }

    private Result helper(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) return new Result(false, false, null);
        Result left = helper(root.left, a, b);
        Result right = helper(root.right, a, b);
        boolean existA = left.existA || right.existA || root == a;
        boolean existB = left.existB || right.existB || root == b;
        TreeNode lca;
        if (root == a || root == b) lca = root;
        else if (left.lca != null && right.lca != null) lca = root;
        else if (left.lca != null && right.lca == null) lca = left.lca;
        else if (left.lca == null && right.lca != null) lca = right.lca;
        else lca = null;
        return new Result(existA, existB, lca);
    }

    public static void main(String[] args) {
        LN578_Lowest_Common_Ancestor_III sol = new LN578_Lowest_Common_Ancestor_III();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n3.left = n4;
        n3.right = n5;
        n5.right = n6;
        System.out.println("LCA: " + sol.lowestCommonAncestor3(n1, n2, n7));
    }
}

class Result {
    boolean existA;
    boolean existB;
    TreeNode lca;

    public Result(boolean existA, boolean existB, TreeNode lca) {
        this.existA = existA;
        this.existB = existB;
        this.lca = lca;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}