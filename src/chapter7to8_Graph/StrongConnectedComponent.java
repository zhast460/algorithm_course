package chapter7to8_Graph;

public class StrongConnectedComponent {

    private boolean[] marked;
    private Iterable<Integer> reversePost;
    private int[] id;
    private int count;

    public StrongConnectedComponent(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;
        reversePost = new TopologicalSort(G.reverse()).reversePost();
        for (int i : reversePost) {
            if (!marked[i]){
                dfs(G, i);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G, w);
            }
        }
    }

    public int id(int v) {
        return id[v];
    }

    public int count(){
        return this.count;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(9);
        G.addEdge(0,1);
        G.addEdge(0,5);
        G.addEdge(2,0);
        G.addEdge(2,3);
        G.addEdge(3,2);
        G.addEdge(3,5);
        G.addEdge(4,3);
        G.addEdge(5,4);
        G.addEdge(4,2);
        G.addEdge(6,0);
        G.addEdge(6,4);
        G.addEdge(6,8);
        G.addEdge(7,6);
        G.addEdge(8,6);

        StrongConnectedComponent scc = new StrongConnectedComponent(G);
        System.out.println("count is " + scc.count());
        for (int i = 0; i < G.V(); i++) {
            System.out.println(scc.id(i));
        }
    }
}
