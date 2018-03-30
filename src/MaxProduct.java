public class MaxProduct {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxProduct = nums[0];
        int preMax = nums[0], preMin = nums[0];

        for(int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int curMax, curMin;
            if(num > 0) {
                curMax = Math.max(num * preMax, num);
                curMin = Math.min(num * preMin, num);
            } else {
                curMin = Math.min(num * preMax, num);
                curMax = Math.max(num * preMin, num);
            }
            maxProduct = Math.max(maxProduct, curMax);
            preMax = curMax;
            preMin = curMin;
        }

        return maxProduct;
    }
}
