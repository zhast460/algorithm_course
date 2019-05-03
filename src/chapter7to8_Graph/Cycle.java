package chapter7to8_Graph;

import java.util.*;

public class Cycle {

    private boolean marked[];
    private int[] edgeTo;
    private List<Integer> cycleList;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int i = 0; i < G.V(); i++){
            if (!marked[i] && cycleList == null)
                dfs(G, -1, i);
        }
    }

    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                // edgeTo update must before dfs, to ensure edgeTo is populated correctly at currently-visit vertex
                edgeTo[w] = v;
                dfs(G, v, w);
            }else if (w != u){
                // the short circuit is important, otherwise the 2nd adj visit at cycle entrance will get infinite loop
                if (cycleList != null) return;
                cycleList = new ArrayList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycleList.add(x);
                }
                cycleList.add(w);
                cycleList.add(v);
            }
        }
    }

    public List getCycleList(){
        return cycleList;
    }

    public static void main(String[] args) {
        Graph G = new Graph(6);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(0, 2);
        G.addEdge(3, 1);
        G.addEdge(3, 4);
        G.addEdge(3, 5);
        G.addEdge(4, 5);

        Cycle c = new Cycle(G);
        System.out.println(c.getCycleList());
    }
}
