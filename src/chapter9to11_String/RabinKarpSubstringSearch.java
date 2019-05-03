package chapter9to11_String;

class RabinKarpSubstringSearch {

    private long patHash; // pattern hash value
    private int M; // pattern length
    private long Q = 997L; // modulus
    private int R = 150; // radix
    private long RM; // R^(M-1) % Q

    public RabinKarpSubstringSearch(String pat) {
        M = pat.length();
        RM = 1;
        for (int i = 1; i < M; i++)
            RM = (RM * R) % Q;
        patHash = hash(pat, pat.length());
    }

    public int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (txtHash == patHash) return 0;
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - (RM * txt.charAt(i-M) % Q)) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (txtHash == patHash) return i - M + 1;
        }
        return -1;
    }

    private long hash(String s, int length) {
        long h = 0;
        for (int i = 0; i < length; i++) {
            h = (h * R + s.charAt(i)) % Q;
        }
        return h;
    }

    public static void main(String[] args) {

        String pat = "ABABAC";
        String s = "sdfasdf321ABSDAFDZVBCABACAABABACBSDDSE123";
        RabinKarpSubstringSearch rk = new RabinKarpSubstringSearch(pat);
        System.out.println(rk.search(s));
    }
}