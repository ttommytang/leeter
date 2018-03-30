public class FirstMissingPositive {
    private int partition(int[] nums) {
        // Helper method to seperate negative and positive numbers
        int left = 0, right = nums.length - 1;
        
        while(left <= right) {
            if (nums[left] > 0) left++;
            else nums[left] = nums[right--];
        }
        
        return left;
    }
    
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int numOfPositives = partition(nums);
        
        for(int i = 0; i < numOfPositives; i++) {
            int cursor = Math.abs(nums[i]);
            if(cursor <= numOfPositives && nums[cursor - 1] > 0) {
                nums[cursor - 1] *= -1;
            }
        }
        
        int i = 0;
        for(; i < numOfPositives; i++) {
            if(nums[i] > 0) break;
        }
        
        return i+1;
    }
}
