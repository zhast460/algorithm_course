package chapter7to8_Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bridge {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (List<Integer> connection : connections){
            graph[connection.get(0)].add(connection.get(1));
            graph[connection.get(1)].add(connection.get(0));
        }

        int[] disc = new int[n], low = new int[n];
        Arrays.fill(disc, -1);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if (disc[i] == -1)
                dfs(i, -1, graph, disc, low, res);
        }
        return res;
    }

    int level = 0;
    private void dfs(int u, int pre, List<Integer>[] graph, int[] disc, int[] low, List<List<Integer>> res){
        low[u] = disc[u] = ++level;
        for (int v : graph[u]){
            if (v == pre) continue;
            if (disc[v] == -1){
                dfs(v, u, graph, disc, low, res);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]){
                    res.add(Arrays.asList(u, v));
                }
            }else{
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args){
        List<List<Integer>> con = new ArrayList<>();
        con.add(Arrays.asList(0 ,1));
        con.add(Arrays.asList(0 ,2));
        con.add(Arrays.asList(2 ,1));
        con.add(Arrays.asList(3 ,1));
        Bridge sol = new Bridge();
        System.out.println(sol.criticalConnections(4, con));
    }
}
