package chapter7to8_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class FoldFulkersonMaxFlow {

    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;

    public FoldFulkersonMaxFlow(FlowNetwork G, int s, int t) {
        value = 0.0;
        double bottleNeck = Double.POSITIVE_INFINITY;
        while (hasAugmentingPath(G, s, t)){
            for (int v = t; v != s; v = edgeTo[v].other(v))
                bottleNeck = Math.min(bottleNeck, edgeTo[v].residualCapacityTo(v));

            for (int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottleNeck);

            value += bottleNeck;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (FlowEdge edge : G.adj(v)) {
                int w = edge.other(v);
                if (edge.residualCapacityTo(w) > 0 && !marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = edge;
                    queue.add(w);
                }
            }
        }
        return marked[t];
    }

    public double value(){return value;}

    public static void main(String[] args) {
        FlowNetwork G = new FlowNetwork(6);
        G.addEdge(0,1,2.0);
        G.addEdge(0,2,3.0);
        G.addEdge(1,3,3.0);
        G.addEdge(1,4,1.0);
        G.addEdge(2,3,1.0);
        G.addEdge(2,4,1.0);
        G.addEdge(3,5,2.0);
        G.addEdge(4,5,3.0);
        FoldFulkersonMaxFlow ff = new FoldFulkersonMaxFlow(G, 0, 5);
        System.out.println(ff.value);
        System.out.println("Hello world");
    }
}
