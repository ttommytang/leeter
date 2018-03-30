public class RotatedSortedArray {
    
    // Solution for LEETCODE #153 - MIN IN ROTATED ARRAY
    public int findMin(int[] nums) {
        if (nums == null || nums.length <= 0) return 0;
        
        int left = 0, right = nums.length - 1, mid = 0;
        while(left < right - 1) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[left] && nums[mid] > nums[right]) left = mid;
            else right = mid;
        }
        return Math.min(nums[left], nums[right]);
    }
    
    // Solution for LEETCODE #033 - SEARCH IN ROTATED ARRAY
    public int searchInRotatedArray(int[] nums, int target) {
        if (nums == null || nums.length <= 0) return -1;
        
        // Find the rotate pivot first (min)
        int left = 0, right = nums.length - 1, mid;
        while(left < right) {
            mid = left + (right - left)/2;
            if (nums[mid] > nums[left]) left = mid + 1;
            else right = mid;
        }
        int pivot = left;
        
        left = 0;
        right = nums.length - 1;
        while(left <= right) {
            mid = left + (right - left)/2;
            int unrotatedMid = mid + pivot;
            if (nums[unrotatedMid] == target) return mid;
            if (nums[unrotatedMid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}
