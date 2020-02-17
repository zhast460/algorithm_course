import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int fullMask = (1 << n) - 1;

        Set<String> visited = new HashSet<>();
        Queue<Node> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Node node = new Node(i, 1<<i);
            que.offer(node);
            visited.add(node.toString());
        }

        int level = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Node node = que.poll();
                if (node.mask == fullMask) return level;
                for (int next : graph[node.id]) {
                    Node nextNode = new Node(next, node.mask | (1 << next));
                    if (visited.contains(nextNode.toString())) continue;
                    que.offer(nextNode);
                    visited.add(nextNode.toString());
                }
            }
            level++;
        }

        return level;
    }

    class Node {
        int id;
        int mask;

        Node(int id, int mask){
            this.id = id; this.mask = mask;
        }

        public String toString() {
            return id + " " + mask;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] graph = new int[][]{{1,2,3},{0},{0},{0}};
        System.out.println(sol.shortestPathLength(graph));
    }
}

/* Definition for a binary tree node. */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}