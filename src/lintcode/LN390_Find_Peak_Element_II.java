package lintcode;

import java.util.Arrays;
import java.util.List;

public class LN390_Find_Peak_Element_II {

    public List<Integer> findPeakII(int[][] A) {
        int lo = 1, hi = A.length - 2;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            int col = find(A, mid);
            if (A[mid-1][col] > A[mid][col]) {
                hi = mid;
            } else if (A[mid+1][col] > A[mid][col]) {
                lo = mid;
            } else {
                lo = mid;
                hi = mid;
                break;
            }
        }
        int col = find(A, lo);
        if (A[lo][col] >= A[hi][col]){
            return Arrays.asList(lo, col);
        } else{
            return Arrays.asList(hi, find(A, hi));
        }
    }

    private int find(int[][] A, int row) {
        int col = 0;
        for (int i = 0; i < A[0].length; i++) {
            if (A[row][i] > A[row][col])
                col = i;
        }
        return col;
    }

    public static void main(String[] args) {
        LN390_Find_Peak_Element_II sol = new LN390_Find_Peak_Element_II();
        int[][] a  = {
                {1 ,2 ,3 ,6 ,5},
                {16,41,23,22,6},
                {15,17,24,21,7},
                {14,18,19,20,10},
                {13,14,11,10,9}};
        System.out.println(sol.findPeakII(a));
    }
}
