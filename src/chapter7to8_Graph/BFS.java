package chapter7to8_Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {

    private boolean[] marked;
    private int edgeTo[];
    public int distTo[];
    private int s;

    public BFS(Graph G, int s){
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        bfs(G, s);
    }

    public void bfs(Graph G, int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        marked[v] = true;
        while (!queue.isEmpty()){
            int w = queue.remove();
            for (int x : G.adj(w)){
                if (!marked[x]) {
                    marked[x] = true;
                    edgeTo[x] = w;
                    distTo[x] = distTo[w] + 1;
                    queue.add(x);
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

    public int distTo(int v) {
        return distTo[v];
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

        BFS bfs = new BFS(G, 0);
        System.out.println(bfs.hasPathTo(3));
        System.out.println(bfs.hasPathTo(6));
        System.out.println(bfs.pathTo(3));
        System.out.println(bfs.pathTo(6));

        for (int i : bfs.distTo)
            System.out.println(i);
    }
}
