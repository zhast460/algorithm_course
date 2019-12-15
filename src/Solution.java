import java.util.ArrayList;
import java.util.List;

class Solution {
    List<Integer> res = new ArrayList();
    public List<Integer> sequentialDigits(int low, int high) {
        String start = String.valueOf(low);
        String end = String.valueOf(high);
        int lo = start.length(), hi = end.length();
        if (lo > 9) return res;
        gen(lo, low, high, true);
        for (int i = lo + 1; i < hi; i++) {
            gen(i, low, high, false);
        }
        if (lo < hi) gen(hi, low, high, true);
        return res;
    }

    private void gen(int d, int low, int high, boolean check) {
        if (d > 9) return;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= d; i++) {
            sb.append(i);
        }
        do {
            int num = Integer.valueOf("" + sb.toString());
            if (check && num >= low && num <= high) res.add(num);
            int lastDigit = sb.charAt(sb.length() - 1) - '0' + 1;
            if (lastDigit == 10) break;
            sb.append(lastDigit);
            sb.deleteCharAt(0);
        } while (true);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.sequentialDigits(100, 300));
    }
}