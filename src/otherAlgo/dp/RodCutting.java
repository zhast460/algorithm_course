package otherAlgo.dp;

public class RodCutting {

    public static void bottomUpCutRod(int[] p, int[] r, int[] s, int n) {
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                if (p[i] + r[j-i] > q){
                    q = p[i] + r[j-i];
                    s[j] = i;
                }
            }
            r[j] = q;
        }
    }

    public static void topDownWithMemo(int[] p, int[] r, int[] s, int n) {
        r[0] = 0;
        for (int i = 1; i <= n; i++) {
            r[i] = -1; // denote it hasn't been computed yet
        }
        topDownWithMemoAux(p, r, s, n);
    }

    // this is to compute r[n] actually
    private static int topDownWithMemoAux(int[] p, int[] r, int[] s, int n) {
        if (r[n] >= 0) return r[n];
        int q = Integer.MIN_VALUE;
        int temp;
        for (int i = 1; i <= n; i++) {
            if ((temp = p[i] + topDownWithMemoAux(p, r, s, n-i)) > q){
                q = temp;
                s[n] = i;
            }
        }
        r[n] = q;
        return q;
    }

    public static void printCutRodSolution(int[] p, int n) {
        int[] r = new int[n+1];
        int[] s = new int[n+1];
        bottomUpCutRod(p, r, s, n);
        //topDownWithMemo(p, r, s, n);
        while (n > 0) {
            System.out.print(s[n] + " ");
            n -= s[n];
        }
    }

    public static void main(String[] args) {
        int n = 10;
        int[] p = {-1, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        printCutRodSolution(p, n);
    }
}
