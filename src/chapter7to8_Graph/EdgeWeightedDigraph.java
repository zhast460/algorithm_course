package chapter7to8_Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private List<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<DirectedEdge>[]) new List[V];
        for (int i = 0; i < V; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(DirectedEdge DirectedEdge) {
        int v = DirectedEdge.from();
        adj[v].add(DirectedEdge);
        E++;
    }

    public void addEdge(int v, int w, double weight) {
        DirectedEdge e = new DirectedEdge(v, w ,weight);
        addEdge(e);
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Set<DirectedEdge> set = new HashSet<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge e : adj[i]) {
                set.add(e);
            }
        }
        return set;
    }

    public int V(){
        return this.V;
    }

    public int E(){
        return E;
    }
}
