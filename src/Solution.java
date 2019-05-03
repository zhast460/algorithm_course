import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Solution {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastScanner in = new FastScanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        A solver = new A();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class A {
        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            out.printf("Case #%d: ", testNumber);
            int n = in.nextInt(), m = in.nextInt();
            int[] x1 = new int[n];
            int[] y1 = new int[n];
            int[] x2 = new int[n];
            int[] y2 = new int[n];
            Set<Integer> setX = new HashSet<>();
            Set<Integer> setY = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int x = in.nextInt(), y = in.nextInt();
                char dir = in.next().charAt(0);
                if (dir == 'N') {

                    x1[i] = 0;
                    x2[i] = m;
                    y1[i] = y + 1;
                    y2[i] = m;
                } else if (dir == 'S') {

                    x1[i] = 0;
                    x2[i] = m;
                    y1[i] = 0;
                    y2[i] = y - 1;
                } else if (dir == 'W') {

                    x1[i] = 0;
                    x2[i] = x - 1;
                    y1[i] = 0;
                    y2[i] = m;
                } else {

                    x1[i] = x + 1;
                    x2[i] = m;
                    y1[i] = 0;
                    y2[i] = m;
                }
                setX.add(x1[i]);
                setX.add(x2[i]);
                setY.add(y1[i]);
                setY.add(y2[i]);
            }
            setX.add(0);
            setX.add(m);
            setY.add(0);
            setY.add(m);
            int[] allX = new int[setX.size()];
            int[] allY = new int[setY.size()];
            int ptr = 0;
            for (int i : setX) {
                allX[ptr++] = i;
            }
            ptr = 0;
            for (int i : setY) {
                allY[ptr++] = i;
            }
            int max = 0, bestX = -1, bestY = -1;
            for (int x : allX) {
                for (int y : allY) {
                    int cnt = 0;
                    for (int i = 0; i < n; i++) {
                        if (x1[i] <= x && x <= x2[i] && y1[i] <= y && y <= y2[i]) {
                            cnt++;
                        }
                    }
                    if (cnt > max || (cnt == max && x < bestX) || (cnt == max && x == bestX && y < bestY)) {
                        max = cnt;
                        bestX = x;
                        bestY = y;
                    }
                }
            }
            out.println(bestX + " " + bestY);
        }

    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        public FastScanner(String fileName) {
            try {
                br = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                String line = null;
                try {
                    line = br.readLine();
                } catch (IOException e) {
                }
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

    }
}

