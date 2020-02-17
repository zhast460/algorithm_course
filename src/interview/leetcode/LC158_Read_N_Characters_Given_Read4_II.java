package interview.leetcode;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.hash;

public class LC158_Read_N_Characters_Given_Read4_II {
    public int assignBike(int[][] workers, int[][] bikes){
        boolean[] bikeUsed = new boolean[bikes.length];
        Map<Integer, Integer> memo = new HashMap<>();
        return backTrack(workers, bikes, 0, bikeUsed, memo);
    }

    private int backTrack(int[][] workers, int[][] bikes, int wid, boolean[] bikeUsed, Map<Integer, Integer> memo){
        if (wid == workers.length) return 0;
        int hashValue = hash(wid, makeString(bikeUsed));
        if (memo.containsKey(hashValue)) return memo.get(hashValue);

        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++){
            if (bikeUsed[i]) continue;
            bikeUsed[i] = true;
            minDis = Math.min(minDis, backTrack(workers, bikes, wid + 1, bikeUsed, memo) + dist(workers[wid], bikes[i]));
            bikeUsed[i] = false;
        }
        memo.put(hashValue, minDis);
        return minDis;
    }

    private String makeString(boolean[] a){
        StringBuilder sb = new StringBuilder();
        for (boolean b : a) sb.append(b ? "1" : "0");
        return sb.toString();
    }

    private int dist(int[] w, int[] b) {
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
    }

    public static void main(String[] args) {
        LC158_Read_N_Characters_Given_Read4_II sol = new LC158_Read_N_Characters_Given_Read4_II();
        int[][] workers = new int[][]{{1,3},{12,4}};
        int[][] bikes = new int[][]{{9,4},{22,3}};
        int res = sol.assignBike(workers, bikes);
        System.out.println(res);
    }
}
