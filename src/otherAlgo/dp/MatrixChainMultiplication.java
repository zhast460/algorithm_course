package otherAlgo.dp;

public class MatrixChainMultiplication {

    public static void matrixChainOrder(int[] p, long[][] m, int[][] s) {
        int n = p.length - 1;
        for (int i = 0; i < n; i++)
            m[i][i] = 0;
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                long q = Long.MAX_VALUE;
                long temp;
                for (int k = i; k < j; k++) {
                    if ((temp = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1]) < q) {
                        q = temp;
                        s[i][j] = k;
                    }
                }
                m[i][j] = q;
            }
        }
    }

    public static void printSolution(int[][] s, int i, int j) {
        if (i == j) System.out.print("A" + (i+1));
        else{
            System.out.print("(");
            printSolution(s, i, s[i][j]);
            printSolution(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        int[] p = {30,35,15,5,10,20,25};
        int n = p.length - 1;
        long[][] m = new long[n][n];
        int[][] s = new int[n][n];
        matrixChainOrder(p, m, s);
        System.out.println("Computation cose: " + m[0][n-1]);
        System.out.print("Solution: ");
        printSolution(s, 0, n-1);
    }
}
