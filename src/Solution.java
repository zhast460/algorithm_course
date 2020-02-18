import java.util.*;

class Solution {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // LC863
        if (root == null || K == 0) return Arrays.asList(target.val);
        Map<Integer, Set<Integer>> graph = new HashMap();
        graph.put(root.val, new HashSet<>());
        dfs(root.left, root, graph);
        dfs(root.right, root, graph);
        return bfs(target, graph, K);
    }

    private void dfs(TreeNode root, TreeNode caller, Map<Integer, Set<Integer>> graph) {
        if (root == null) return;
        graph.putIfAbsent(root.val, new HashSet());
        graph.putIfAbsent(caller.val, new HashSet());
        graph.get(root.val).add(caller.val);
        graph.get(caller.val).add(root.val);
        dfs(root.left, root, graph);
        dfs(root.right, root, graph);
    }

    private List<Integer> bfs(TreeNode target, Map<Integer, Set<Integer>> graph, int K) {
        List<Integer> res = new ArrayList();
        Queue<Integer> queue = new LinkedList();
        Set<Integer> visited = new HashSet<>();
        queue.offer(target.val);
        visited.add(target.val);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int curr = queue.poll();
                for (int nei : graph.get(curr)) {
                    if (visited.contains(nei)) continue;
                    queue.offer(nei);
                    visited.add(nei);
                }
            }
            if (level == K - 1) {
                res.addAll(queue);
                break;
            } else {
                level++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = TreeNode.deserialize("[3,5,1,6,2,0,8,null,null,7,4]");
        System.out.println(sol.distanceK(root, new TreeNode(5),2));
    }
}