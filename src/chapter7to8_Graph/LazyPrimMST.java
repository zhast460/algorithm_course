package chapter7to8_Graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];
        mst = new LinkedList<>();
        pq = new PriorityQueue<>();
        visit(graph, 0);
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge e = pq.remove();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.add(e);
            if (!marked[v])
                visit(graph, v);
            else
                visit(graph, w);
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            if (!marked[e.other(v)])
                pq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(8);
        G.addEdge(4,5,0.35);
        G.addEdge(4,7,0.37);
        G.addEdge(5,7,0.28);
        G.addEdge(0,7,0.16);
        G.addEdge(1,5,0.32);
        G.addEdge(0,4,0.38);
        G.addEdge(2,3,0.17);
        G.addEdge(1,7,0.19);
        G.addEdge(0,2,0.26);
        G.addEdge(1,2,0.36);
        G.addEdge(1,3,0.29);
        G.addEdge(2,7,0.34);
        G.addEdge(6,2,0.40);
        G.addEdge(3,6,0.52);
        G.addEdge(6,0,0.58);
        G.addEdge(6,4,0.93);

        LazyPrimMST mst = new LazyPrimMST(G);
        System.out.println(mst.edges());
    }
}
