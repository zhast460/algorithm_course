import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SolutionTemplateForFB {

    public int solve(int n) {
        return n;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SolutionTemplateForFB sol = new SolutionTemplateForFB();
        Scanner in = readFromConsole ? new Scanner(System.in) : new Scanner(INPUT_FILE);
        int T = in.nextInt();
        for (int ks = 1; ks <= T; ++ks) {
            int n = in.nextInt();
            System.out.println("Case #" + ks + ": " + sol.solve(n));
        }
    }

    public static final boolean readFromConsole = true;
    public static final String INPUT_FILENAME = "alchemy_input.txt";

    public static final String DOWNLOAD_DIR = "/Users/scott/Downloads/";
    public static final File INPUT_FILE = new File(DOWNLOAD_DIR + INPUT_FILENAME);
}