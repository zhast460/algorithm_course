import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

// BIG LEARNING - java TreeSet/TreeMap couldn't store duplicate, even with "key -> another key" mapping as comparator,
// whether "another key" will be compared and "another key" could have duplicates.

public class FB_HackerCup_2020_Qualification_D1 {

    public long solve(int n, int m, int[] a) {
        a[0] = 0;
        long[] dp = new long[n];
        long[] cost = new long[n];
        TreeMap<Long, Integer> map = new TreeMap(); // we need a sorted structure that support storing duplicate and efficient removal, thus create a val -> count map
        if (m >= n - 1) return 0;
        for (int i = 1; i < m; i++) {
            cost[i] = a[i];
            if (a[i] > 0) map.compute(cost[i], (k, v) -> v == null ? 1 : v + 1); // cost = 0 if a = 0
        }
        for (int i = m + 1; i < n; i++) {
            dp[i] = Long.MAX_VALUE;
            if (a[i-1] > 0) {
                cost[i-1] = dp[i-1] + a[i-1];
                map.compute(cost[i-1], (k, v) -> v == null ? 1 : v + 1); // cost = 0 if a = 0
            }
            if (i - m - 1 >= 1 && cost[i-m-1] > 0) // if cost[i-m-1] has ever been put into the map -> cost[i-m-1] > 0
                map.compute(cost[i-m-1], (k, v) -> v == 1 ? null : v - 1);
            if (map.isEmpty())
                return -1;
            else dp[i] = map.firstKey();
        }
        return dp[n-1];
    }

    public static void main(String[] args) throws IOException {
        FB_HackerCup_2020_Qualification_D1 sol = new FB_HackerCup_2020_Qualification_D1();
        Scanner in = readFromConsole ? new Scanner(System.in) : new Scanner(INPUT_FILE);
        if (!OUTPUT_FILE.exists()) OUTPUT_FILE.createNewFile();
        PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_FILE));

        int T = in.nextInt();
        for (int ks = 1; ks <= T; ++ks) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            String ans = "Case #" + ks + ": " + sol.solve(n, m, a);
            System.out.println(ans);
            pw.println(ans);
        }
        pw.close();
    }

    public static final boolean readFromConsole = !true;
    public static final String INPUT_FILENAME = "running_on_fumes_chapter_1_input (1).txt";
    public static final String OUTPUT_FILENAME = "out.txt";

    public static final String DOWNLOAD_DIR = "/Users/scott/Downloads/";
    public static final File INPUT_FILE = new File(DOWNLOAD_DIR + INPUT_FILENAME);
    public static final File OUTPUT_FILE = new File(DOWNLOAD_DIR + OUTPUT_FILENAME);
}