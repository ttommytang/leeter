import java.util.*;

public class TriangleSets {
    public static void main(String[] args) {
        TriangleSets test = new TriangleSets();
        int[] arr = {2,2,3,4};
        
        int res = test.triangleNumber(arr);
    }
    
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        
        int count = 0, len = nums.length;
        Arrays.sort(nums);
        
        for (int idxC = len - 1; idxC > 1; idxC--) {
            int i = 0, j = idxC - 1;
            while(i < j) {
                if (nums[i] + nums[j] > nums[idxC]) {
                    count += j-i;
                    j--;
                } else i++;
            }
        }
        
        return count;
    }
}
