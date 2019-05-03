package chapter7to8_Graph;

import java.util.LinkedList;
import java.util.List;

// this is a adjacency list implementation
public class Digraph {

    private final int V;
    private int E;
    private List<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Integer>[]) new List[V];
        for (int i = 0; i < V; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge.to());
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V(){
        return this.V;
    }

    public int E(){
        return this.E;
    }

    public Digraph reverse(){
        Digraph digraph = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : this.adj(v))
                digraph.addEdge(w, v);
        return digraph;
    }
}
