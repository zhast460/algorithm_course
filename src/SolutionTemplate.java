import java.util.Scanner;

public class SolutionTemplate {

    public int solve(int n) {
        return n;
    }

    public static void main(String[] args) {
        SolutionTemplate sol = new SolutionTemplate();
        Scanner in = new Scanner((System.in));
        int T = in.nextInt();
        for (int ks = 1; ks <= T; ++ks) {
            int n = in.nextInt();

            String ans = "Case #" + ks + ": " + sol.solve(n);
            System.out.println(ans);
        }
    }
}