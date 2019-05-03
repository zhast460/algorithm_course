package chapter7to8_Graph;

import java.util.Deque;
import java.util.LinkedList;

// this is a topological sort / reverse postorder algo / Depth First Order
public class TopologicalSort {

    boolean[] mark;
    Deque<Integer> stack;

    public TopologicalSort(Digraph digraph){
        mark = new boolean[digraph.V()];
        stack = new LinkedList<>();
        for (int v = 0; v < digraph.V(); v++){
            if (!mark[v])
                dfs(digraph, v);
        }

    }

    private void dfs(Digraph digraph, int v) {
        mark[v] = true;
        for (int w : digraph.adj(v)) {
            if (!mark[w]) {
                dfs(digraph, w);
            }
        }
        stack.addFirst(v);
    }

    public Iterable<Integer> reversePost(){
        return stack;
    }

    public static void main(String[] args) {
        Digraph d = new Digraph(7);
        d.addEdge(0, 1);
        d.addEdge(0, 2);
        d.addEdge(0, 5);
        d.addEdge(5, 2);
        d.addEdge(1, 4);
        d.addEdge(3, 2);
        d.addEdge(3, 5);
        d.addEdge(3, 4);
        d.addEdge(3, 6);
        d.addEdge(6, 4);
        d.addEdge(6, 0);

        TopologicalSort dfo = new TopologicalSort(d);
        for (int i : dfo.reversePost())
            System.out.println(i);
    }
}
