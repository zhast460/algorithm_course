package chapter7to8_Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private List<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Edge>[]) new List[V];
        for (int i = 0; i < V; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(Edge edge) {
        int v = edge.either(), w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public void addEdge(int v, int w, double weight) {
        Edge e = new Edge(v, w ,weight);
        addEdge(e);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges(){
        Set<Edge> set = new HashSet<>();
        for (int i = 0; i < V; i++) {
            for (Edge e : adj[i]) {
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
