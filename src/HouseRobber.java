public class HouseRobber {
    public static void main(String[] args) {
        HouseRobber test = new HouseRobber();
        
        int res = test.rob(new int[]{2,1,1,2});
    }
    
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int[] dp = new int[len];
        
        for (int i = 0; i < len; i++) {
            int rob = nums[i] + (i >= 2 ? nums[i-2] : 0);
            int notRob = i >= 1 ? dp[i-1] : 0;
            dp[i] = Math.max(rob, notRob);
        }
        return dp[len-1];
    }
}
