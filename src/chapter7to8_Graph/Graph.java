package chapter7to8_Graph;

import java.util.LinkedList;
import java.util.List;

// this is a adjacency list implementation
public class Graph {

    private final int V;
    private int E;
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Integer>[]) new List[V];
        for (int i = 0; i < V; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V(){
        return this.V;
    }

    public int E(){
        return E;
    }
}
