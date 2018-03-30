public class FindDuplicateInRangedArray {
    public static void main(String[] args) {
    
    }
    
    // Solution for LEETCODE #287. Find the Duplicate Number
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length < 2) return -1;
        int left = 1, right = nums.length - 1, mid;
        while(left < right) {
            mid = left + (right - left) / 2;
            int count = 0;
            for (int i : nums) {
                if ( i <= mid) count++;
            }
            
            if (count > mid)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
