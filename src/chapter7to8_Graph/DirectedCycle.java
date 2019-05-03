package chapter7to8_Graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedCycle {

    private boolean marked[];
    private int[] edgeTo;
    private boolean onStack[];
    private List<Integer> cycleList;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++){
            if (!marked[i] && cycleList == null)
                dfs(G, i);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                // edgeTo update must before dfs, to ensure edgeTo is populated correctly at currently-visit vertex
                edgeTo[w] = v;
                dfs(G, w);
            }else if (onStack[w]){
                if (cycleList != null) return;
                cycleList = new ArrayList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycleList.add(x);
                }
                cycleList.add(w);
                cycleList.add(v);
            }
        }
        onStack[v] = false;
    }

    public List getCycleList(){
        return cycleList;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(5);
        G.addEdge(0, 1);
        G.addEdge(1, 3);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(4, 2);

        DirectedCycle c = new DirectedCycle(G);
        System.out.println(c.getCycleList());
    }
}
