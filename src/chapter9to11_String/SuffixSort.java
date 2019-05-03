package chapter9to11_String;

import java.util.Arrays;

public class SuffixSort {

    static String suffixSort(String s){
        int N = s.length();
        String[] sa = new String[N];
        for (int i = 0; i < N; i++){
            sa[i] = s.substring(i);
        }
        Arrays.sort(sa);
        int len = 0, temp;
        String lcp = "";
        for (int i = 0; i < N - 1; i++){
            if ((temp = lcp(sa[i], sa[i+1])) > len){
                len = temp;
                lcp = sa[i].substring(0, len);
            }
        }
        return lcp;
    }

    static int lcp(String a, String b) {
        int n = Math.min(a.length(), b.length());
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i))
                return i;
        }
        return n;
    }


    public static void main(String[] args) {
        String s = "CallmeIshmael.Someyearsago-nevermindhowlongprecisely-nearlythesamefeelingstowardstheoceanwithme.";
        System.out.println(suffixSort(s));
    }
}
