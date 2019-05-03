package chapter9to11_String;

class KMPsubstringSearch {

    int[][] dfa(String pat) {
        int M = pat.length();
        int R = 200;
        int[][] dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int j = 1, X = 0; j < M; j++) {
            for (int i = 0; i < R; i++)
                dfa[i][j] = dfa[i][X];
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }
        return dfa;
    }

    int indexOf(String s, String pat) {
        int N = s.length();
        int M = pat.length();
        int[][] dfa = dfa(pat);
        int i, X;
        for (X = 0, i = 0; i < N && X < M; i++) {
            X = dfa[s.charAt(i)][X];
        }
        if (X == M) return i - M;
        else return -1;
    }

    public static void main(String[] args) {

        String pat = "ABABAC";
        String s = "sdfasdf321ABSDAFDZVBCABACAABABACBSDDSE123";
        KMPsubstringSearch kmp = new KMPsubstringSearch();
        System.out.println(kmp.indexOf(s, pat));
    }
}