package interview.lintcode;

class LN183_Wood_Cut {
    public int woodCut(int[] L, int k) {
        if (L.length == 0) return 0;
        int lo = 0, hi = Integer.MIN_VALUE;
        for (int l : L) {
            if (l > hi) {
                hi = l;
            }
        }
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(L, k, mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return check(L, k, hi) ? hi : lo;
    }

    private boolean check(int[] L, int k, int ans) {
        int res = 0;
        for (int l : L) {
            res += l / ans;
        }
        return res >= k;
    }

    public static void main(String[] args) {
        LN183_Wood_Cut sol = new LN183_Wood_Cut();
        int[] a  = {232,124,456};
        System.out.println(sol.woodCut(a, 7));
    }
}