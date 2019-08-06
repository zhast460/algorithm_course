package leetcode;

public class LC308_Range_Sum_Query_2D_Mutable {
    int[][] bitArray; // binary index tree array
    int[][] nums;

    public LC308_Range_Sum_Query_2D_Mutable(int[][] nums) {
        this.nums = nums;
        int r = nums.length, c = nums[0].length;
        bitArray = new int[r][c+1];
        for (int i = 0; i < r; i++){
            for (int j = 1; j < c + 1; j++){
                add(nums[i][j-1], i, j);
            }
        }
    }

    public void update(int val, int i, int j) {
        int delta = val - nums[i][j];
        nums[i][j] = val;
        add(delta, i, j + 1);
    }

    public int regionSum(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i <= row2; i++) {
            res += rowSum(i, col2 + 1) - rowSum(i, col1);
        }
        return res;
    }

    private int rowSum(int i, int j) {
        int res = 0;
        while (j > 0){
            res += bitArray[i][j];
            j -= j & -j;
        }
        return res;
    }

    private void add(int delta, int i, int j) {
        while (j < bitArray[0].length){
            bitArray[i][j] += delta;
            j += j & -j;
        }
    }

    public static void main(String[] arsg){
        int[][] a = {{-1,0,9},{2,5,-2},{7,1,2}};
        LC308_Range_Sum_Query_2D_Mutable sol = new LC308_Range_Sum_Query_2D_Mutable(a);
        System.out.println(sol.regionSum(1, 1, 2, 2));
        sol.update(3, 1,2);
        System.out.println(sol.regionSum(1, 1, 2, 2));
    }
}
