package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC392_Is_Subsequence_followup {

    List<Integer>[] indexs;

    public LC392_Is_Subsequence_followup (String t) {
        indexs = new List[256];
        char[] ca = t.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (indexs[ca[i]] == null) indexs[ca[i]] = new ArrayList<>();
            indexs[ca[i]].add(i); // stores the indexes of char's occurrence in t, the list is in ascending order
        }
    }

    public boolean isSubsequence(String s) {
        int prev = -1;
        for (char c : s.toCharArray()) {
            List<Integer> list = indexs[c];
            if (list == null) return false;
            int idx = Collections.binarySearch(list, prev);
            if (idx < 0) idx = ~idx;
            if (idx == list.size()) return false; // no larger index than the previous char
            prev = list.get(idx) + 1; // binary search return a num's "ceiling", which includes inself.
            // so here add by 1 to make sure next searched item is greater than prev
        }
        return true;
    }

    public static void main(String[] args) {
        LC392_Is_Subsequence_followup sol = new LC392_Is_Subsequence_followup("abcdefg");
        System.out.println(sol.isSubsequence("beg"));
        System.out.println(sol.isSubsequence("cfa"));
    }
}
