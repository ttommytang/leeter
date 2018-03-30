public class FindPeakElement {
    public static void main(String[] args) {
        FindPeakElement testy = new FindPeakElement();
    
        int[] arrayToFindPeakA = {1};
        int[] arrayToFindPeakB = {1, 2};
        int[] arrayToFindPeakC = {3, 2, 1, 8};
        int[] arrayToFindPeakD = {1, 3, 2};

        assert (testy.findPeakElement(arrayToFindPeakA) == 0);
        assert (testy.findPeakElement(arrayToFindPeakB) == 1);
        assert (testy.findPeakElement(arrayToFindPeakC) == 0);
        assert (testy.findPeakElement(arrayToFindPeakD) == 1);
    }
    
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length <= 0) return -1;
        
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            
            if (mid - 1 >= 0 && mid + 1 < nums.length) {
                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;
                else if(nums[mid] >= nums[mid - 1] && nums[mid + 1] >= nums[mid]) left = mid + 1;
                else right = mid - 1;
            } else if (mid + 1 < nums.length) {
                return nums[mid] > nums[right] ? mid : right;
            } else if (mid - 1 >= 0){
                return nums[mid] > nums[left]  ? mid : left;
            } else {
                return mid;
            }
        }
        
        return left;
    }
}
