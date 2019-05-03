package chapter7to8_Graph;

import java.util.Stack;

public class DFS {

    private boolean marked[];
    private int[] edgeTo;
    private int s;

    public DFS(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
        //dfsWithStack(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }

    private void dfsWithStack(Graph G, int v) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while (!stack.empty()){
            int w = stack.pop();
            if (!marked[w]){
                marked[w] = true;
                for (int x : G.adj(w)) {
                    if (!marked[x]) {
                        stack.push(x);
                        edgeTo[x] = w;
                    }
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]){
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) {
        Graph G = new Graph(7);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        G.addEdge(0, 1);
        G.addEdge(3, 2);
        G.addEdge(3, 4);
        G.addEdge(3, 5);
        G.addEdge(4, 2);
        G.addEdge(0, 5);

        DFS dfs = new DFS(G, 0);
        System.out.println(dfs.hasPathTo(5));
        System.out.println(dfs.hasPathTo(6));
        System.out.println(dfs.pathTo(5));
        System.out.println(dfs.pathTo(6));
    }
}
