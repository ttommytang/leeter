import java.util.*;

public class RandomPickIndex {
    public static void main(String[] args) {
    }

    private int[] nums;
    private Random rd;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.rd = new Random();
    }

    public int pick(int target) {
        int count = 0, result = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;

            // Same mechanism as data stream shuffle -> only keep one res, replace it with the new comer if and only if random generated index equal to 0(or new length - 1)
            count++;
            if (rd.nextInt(count) == 0) result = i;
        }
        return result;
    }
}
