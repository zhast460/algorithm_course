package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC248_Strobogrammatic_Number_III {
    public int strobogrammaticInRange(String low, String high) {
        List<String> res = new ArrayList<>();
        for (int i = low.length(); i <= high.length(); i++)
            res.addAll(helper(i, i));
        int cnt = 0;
        for (String s : res) {
            if (s.length() == low.length() && s.compareTo(low) < 0 || s.length() == high.length() && s.compareTo(high) > 0)
                continue;
            cnt++;
        }
        return cnt;
    }

    private List<String> helper(int n, int max) {
        if (n == 0) return Arrays.asList("");
        if (n == 1) return Arrays.asList("0", "1", "8");
        List<String> list = helper(n - 2, max);
        List<String> res = new ArrayList<>();
        for (String s : list) {
            if (n != max) res.add("0" + s + "0");
            res.add("1" + s + "1");
            res.add("8" + s + "8");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
        }
        return res;
    }



    public static void main(String[] args) {
        LC248_Strobogrammatic_Number_III sol = new LC248_Strobogrammatic_Number_III();
        int cnt = sol.strobogrammaticInRange("50", "10000000");
        System.out.println(cnt);
    }
}
