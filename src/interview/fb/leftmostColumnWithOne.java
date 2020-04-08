package interview.fb;

public class leftmostColumnWithOne {
    public static int smallestIndex(int[][] a) {
        if (a.length == 0 || a[0].length == 0) return -1;
        int m = a.length, n = a[0].length, j = n - 1;
        for (int i = 0; i < m && j > 0; i++) {
            while (j > 0 && a[i][j-1] == 1) {
                j--;
            }
        }
        return j;
    }

    public static int smallestIndexBS(int[][] a) {
        if (a.length == 0 || a[0].length == 0) return -1;
        int m = a.length, n = a[0].length;
        if (!hasOne(a, m, n - 1)) return -1;
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (hasOne(a, m, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static boolean hasOne(int[][] a, int m, int col) {
        for (int i = 0; i < m; i++) {
            if (a[i][col] == 1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = {
                {0,0,0,0,0,1,1,1,1},
                {0,0,0,0,0,0,0,1,1},
                {1,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,1,1,1}};
        System.out.println(smallestIndexBS(a));
    }
}
