package interview.leetcode;

import java.util.*;

public class LC1057_Campus_Bikes_I {
    public int[] assignBike(int[][] workers, int[][] bikes){
        List<int[]>[] dis = new ArrayList[2000];
        for (int i = 0; i < workers.length; i++){
            for (int j = 0; j < bikes.length; j++){
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if (dis[dist] == null) dis[dist] = new ArrayList<>();
                dis[dist].add(new int[]{i, j});
            }
        }
        Set<Integer> assigned = new HashSet<>();
        Set<Integer> occupied = new HashSet<>();
        int[] res = new int[workers.length];
        for (int i = 0; i < dis.length; i++){
            if (dis[i] == null) continue;
            for (int[] pair : dis[i]){
                if (!assigned.contains(pair[0]) && !occupied.contains(pair[1])){
                    assigned.add(pair[0]);
                    occupied.add(pair[1]);
                    res[pair[0]] = pair[1];
                    if (assigned.size() == workers.length) return res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LC1057_Campus_Bikes_I sol = new LC1057_Campus_Bikes_I();
        int[][] workers = new int[][]{{6,2},{3,3},{4,2}};
        int[][] bikes = new int[][]{{1,1},{2,1},{5,4},{2,5}};
        int[] res = sol.assignBike(workers, bikes);
        System.out.println(Arrays.toString(res));
    }
}
