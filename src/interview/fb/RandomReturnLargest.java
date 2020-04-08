package interview.fb;

/*
Given an array of integers arr, randomly return an index of the maximum value seen by far.

Example:
Input: [11, 30, 2, 30, 30, 30, 6, 2, 62, 62]

Having iterated up to the at element index 5 (where the last 30 is), randomly give an index among [1, 3, 4, 5] which are indices of 30 - the max value by far. Each index should have a ¼ chance to get picked.

Having iterated through the entire array, randomly give an index between 8 and 9 which are indices of the max value 62.
*/

import java.util.Random;

public class RandomReturnLargest {

    int a[];
    Random random;

    public RandomReturnLargest() {
        a = new int[]{11, 30, 2, 30, 30, 30, 6, 2, 2, 2};
        random = new Random();
    }

    public int returnMaxElementIndex() {
        int res = 0, count = 1, max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                count = 1;
                res = i;
            } else if (a[i] == max) {
                count++;
                if (random.nextInt(count) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RandomReturnLargest sol = new RandomReturnLargest();
        for (int i = 0; i < 10; i++)
            System.out.println(sol.returnMaxElementIndex());
    }
}
