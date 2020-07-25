import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SolutionTemplateForFB {

    public int solve(int n) {
        return n;
    }

    public static void main(String[] args) throws IOException {
        SolutionTemplateForFB sol = new SolutionTemplateForFB();
        Scanner in = readFromConsole ? new Scanner(System.in) : new Scanner(INPUT_FILE);
        if (!OUTPUT_FILE.exists()) OUTPUT_FILE.createNewFile();
        PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_FILE));

        int T = in.nextInt();
        for (int ks = 1; ks <= T; ++ks) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            String ans = "Case #" + ks + ": " + sol.solve(n);
            System.out.println(ans);
            pw.println(ans);
        }
        pw.close();
    }

    public static final boolean readFromConsole = true;
    public static final String INPUT_FILENAME = "in.txt";
    public static final String OUTPUT_FILENAME = "out.txt";

    public static final String DOWNLOAD_DIR = "/Users/scott/Downloads/";
    public static final File INPUT_FILE = new File(DOWNLOAD_DIR + INPUT_FILENAME);
    public static final File OUTPUT_FILE = new File(DOWNLOAD_DIR + OUTPUT_FILENAME);
}