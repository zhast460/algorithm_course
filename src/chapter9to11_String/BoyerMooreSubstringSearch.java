package chapter9to11_String;

class BoyerMooreSubstringSearch {

    static int search(String s, String pat) {
        int M = pat.length();
        int N = s.length();
        int R = 150;
        int[] right = new int[R];
        for (int i = 0; i < pat.length(); i++)
            right[pat.charAt(i)] = i;

        int skip;
        for (int i = 0; i < N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != (s.charAt(i + j))){
                    skip = Math.max(1, j - right[s.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {

        String pat = "ABABAC";
        String s = "sdfasdf321ABSDAFDZVBCABACAABABACBSDDSE123";
        System.out.println(search(s, pat));
    }
}