import java.util.*;

public class ImageSmoother {
    public static void main(String[] args) {
        ImageSmoother test = new ImageSmoother();
        
        int[] arr1 = {1, 0};
        int[] arr2 = {0, 0, 0};
        
        System.out.println(test.findMaxConsecutiveOnesWithOneFlip(arr1));
        System.out.println(test.findMaxConsecutiveOnesWithOneFlip(arr2));
    }
    
    public int[][] imageSmoother(int[][] M) {
        return null;
    }
    
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int longest = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            if (i > 0) nums[i] += nums[i-1];
            longest = Math.max(longest, nums[i]);
        }
        
        return longest;
    }
    
    public int findMaxConsecutiveOnesWithOneFlip(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // Let's try something like finding island
        List<Integer> postCounts = new ArrayList<>();
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (i != 0) postCounts.add(nums[i-1]);
                else postCounts.add(0);
            } else {
                nums[i] += (i > 0 ? nums[i-1] : 0);
            }
        }
        // Add the last island if tail is missed
        postCounts.add(nums[i-1]);
        
        int max = 0, j = 0;
        for (; j < postCounts.size() - 1; j++) {
            max = Math.max(max, postCounts.get(j));
            max = Math.max(max, postCounts.get(j+1));
            max = Math.max(max, postCounts.get(j) + postCounts.get(j+1) + 1);
        }
        max = Math.max(max, postCounts.get(j));
        
        return max;
    }
}
