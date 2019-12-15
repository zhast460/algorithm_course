package otherAlgo.greedy;

import java.util.ArrayList;
import java.util.List;

public class ActivitySelection {
    // this greedy algo assume f is in ascending order
    static List<Integer> select(int[] s, int[] f, int n) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        int last = 1;
        for (int curr = 2; curr <= n; curr++){
            if (s[curr-1] >= f[last-1]) {
                res.add(curr);
                last = curr;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] s = {1,3,0,5,3,5,6,8,8,2,12};
        int[] f = {4,5,6,7,9,9,10,11,12,14,16};
        List<Integer> res = select(s, f, s.length);
        System.out.println(res  );
    }
}
