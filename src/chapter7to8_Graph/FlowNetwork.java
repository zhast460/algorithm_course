package chapter7to8_Graph;

import java.util.LinkedList;
import java.util.List;

public class FlowNetwork {
    private final int V;
    private int E;
    private List<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<FlowEdge>[]) new List[V];
        for (int i = 0; i < V; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(FlowEdge edge) {
        int v = edge.from(), w = edge.to();
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public void addEdge(int v, int w, double capacity) {
        FlowEdge edge = new FlowEdge(v, w, capacity);
        addEdge(edge);
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }
}
