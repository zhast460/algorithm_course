package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC734_Sentence_Similarity {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, Set<String>> dict = new HashMap<>();
        for (String[] pair : pairs){
            if (!dict.containsKey(pair[0])) dict.put(pair[0], new HashSet<>());
            dict.get(pair[0]).add(pair[1]);
            if (!dict.containsKey(pair[1])) dict.put(pair[1], new HashSet<>());
            dict.get(pair[1]).add(pair[0]);
        }

        for (int i = 0; i < words1.length; i++){
            if (words1[i].equals(words2[i])) continue;
            if (!dict.containsKey(words1[i])) return false;
            if (!dict.get(words1[i]).contains(words2[i])) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        LC734_Sentence_Similarity sol = new LC734_Sentence_Similarity();
        String[] w1 = new String[]{"i", "o", "u"};
        String[] w2 = new String[]{"w", "the", "h"};
        String[][] pairs = new String[][]{{"w","i"},{"o","t"},{"t", "the"},{"h","u"}};
        boolean res = sol.areSentencesSimilar(w1, w2, pairs);
        System.out.println(res);
    }
}
