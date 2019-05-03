package chapter7to8_Graph;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Deque;
import java.util.LinkedList;

public class DijkstraSP {

    private double distTo[];
    private DirectedEdge edgeTo[];
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQ(G.V());

        distTo[0] = 0;
        for (int i = 1; i < G.V(); i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
    }

    private void relax(DirectedEdge edge) {
        int w = edge.to();
        int v = edge.from();
        if (distTo[v] + edge.weight() < distTo[w]) {
            distTo[w] = distTo[v] + edge.weight();
            edgeTo[w] = edge;
            if (pq.contains(w))
                pq.decreaseKey(w, distTo[w]);
            else
                pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int w) {
        return distTo[w];
    }

    public Iterable<DirectedEdge> pathTo(int w) {
        Deque<DirectedEdge> stack = new LinkedList<>();
        for (DirectedEdge e = edgeTo[w]; e != null; e = edgeTo[e.from()])
            stack.addFirst(e);
        return stack;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
        G.addEdge(0,1,5.0);
        G.addEdge(0,4,9.0);
        G.addEdge(0,7,8.0);
        G.addEdge(1,2,12.0);
        G.addEdge(1,3,15.0);
        G.addEdge(1,7,4.0);
        G.addEdge(2,3,3.0);
        G.addEdge(2,6,11.0);
        G.addEdge(3,6,9.0);
        G.addEdge(4,5,4.0);
        G.addEdge(4,6,20.0);
        G.addEdge(4,7,5.0);
        G.addEdge(5,2,1.0);
        G.addEdge(5,6,13.0);
        G.addEdge(7,5,6.0);
        G.addEdge(7,2,7.0);

        //DijkstraSP sp = new DijkstraSP(G, 0);
        //System.out.println(sp.distTo(6));
        //System.out.println(sp.pathTo(6));

        EdgeWeightedDigraph negativeWeightG = new EdgeWeightedDigraph(5);
        negativeWeightG.addEdge(0, 3, 2);
        negativeWeightG.addEdge(0, 1, 4);
        negativeWeightG.addEdge(1, 2, 6);
        negativeWeightG.addEdge(2, 3, -9);
        negativeWeightG.addEdge(3, 4, 1);
        DijkstraSP sp2 = new DijkstraSP(negativeWeightG, 0);
        System.out.println(sp2.distTo(4));
    }
}
