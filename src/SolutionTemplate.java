import java.util.Scanner;

public class SolutionTemplate {
    public static void main(String[] args) {
        Scanner in = new Scanner((System.in));
        int T = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int ks = 1; ks <= T; ++ks) {
            int n = in.nextInt();
            int m = in.nextInt();
            System.out.println("Case #" + ks + ": " + (n + m) + " " + (n * m));
        }
    }
}