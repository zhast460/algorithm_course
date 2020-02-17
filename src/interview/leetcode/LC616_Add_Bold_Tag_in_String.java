package interview.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class LC616_Add_Bold_Tag_in_String {
    public String addBoldTag(String s, String[] dict) {
        List<int[]> sorted = new ArrayList<>(); //i[1] is length
        for (String d : dict) sorted.add(new int[]{s.indexOf(d), d.length()});
        sorted = sorted.stream().filter(i -> i[0] > -1).sorted(Comparator.comparing(i -> i[0])).collect(Collectors.toList());

        List<int[]> merged = new ArrayList<>(); //i[1] is end point
        int[] prev = sorted.get(0);
        int[] prevInterval = new int[]{prev[0], prev[0] + prev[1]};
        merged.add(prevInterval);
        for (int[] interval : sorted) {
            int end = interval[0] + interval[1];
            if (interval[0] > prevInterval[1]) {
                int[] curr = new int[]{interval[0], end};
                merged.add(curr);
                prevInterval = curr;
            }else{
                prevInterval[1] = Math.max(prevInterval[1], end);
            }
        }

        for (int i = merged.size() - 1; i >= 0; i--) {
            int[] interval = merged.get(i);
            s = s.substring(0, interval[0]) + "<b>" + s.substring(interval[0], interval[1]) + "</b>" + s.substring(interval[1]);
        }

        return s;
    }

    public static void main(String[] args) {
        LC616_Add_Bold_Tag_in_String sol = new LC616_Add_Bold_Tag_in_String();
        System.out.println(sol.addBoldTag("aaabbcc", new String[]{"aaa","aab", "abc","bc"}));
    }
}
