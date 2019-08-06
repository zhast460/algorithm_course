package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC247_Strobogrammatic_Number_II {
    public List<String> findStrobogrammatic(int n) {
        return help(n, n);
    }

    private List<String> help(int n, int N) {
        if (n == 0) return Arrays.asList("");
        if (n == 1) return Arrays.asList("0", "1", "8");
        List<String> list = help(n - 2, N);
        List<String> res = new ArrayList<>();
        for (String s : list) {
            if (n != N) res.add("0" + s + "0");
            res.add("1" + s + "1");
            res.add("8" + s + "8");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
        }
        return res;
    }

    public static void main(String[] args) {
        LC247_Strobogrammatic_Number_II sol = new LC247_Strobogrammatic_Number_II();
        List<String> list = sol.findStrobogrammatic(4);
        System.out.println(list);
    }
}
