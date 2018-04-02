import java.util.*;

public class RearrangeByEvenOdd {
    public static void main(String[] args) {
        int[] arr1 = {};
        int[] arr2 = {};
    }
    public int[] rearrange(int[] nums) {
        // Write your code here
        if (nums == null || nums.length <= 1) return nums;
        
        Arrays.sort(nums);
        int[] res = new int[nums.length];
        
        int even = 0, odd = 1, mid = (nums.length + 1)/2;
        for (int i = 0; i < res.length; i++) {
            if (i % 2 == 0)
                res[i] = nums[i/2];
            else
                res[i] = nums[mid + i/2];
        }
        return res;
    }
}
