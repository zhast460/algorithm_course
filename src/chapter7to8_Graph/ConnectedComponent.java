package chapter7to8_Graph;

public class ConnectedComponent {

    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponent(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]){
                dfs(G, i);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
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
        Graph G = new Graph(8);
        G.addEdge(0, 2);
        //G.addEdge(1, 2);
        //G.addEdge(0, 1);
        G.addEdge(3, 2);
        G.addEdge(3, 4);
        G.addEdge(3, 5);
        G.addEdge(4, 2);
        G.addEdge(0, 5);

        ConnectedComponent cc = new ConnectedComponent(G);
        System.out.println("count is " + cc.count());
        for (int i = 0; i < G.V(); i++) {
            System.out.println(cc.id(i));
        }
    }
}
