public class JumpGameII {
    public static void main(String[] args) {
        JumpGameII test = new JumpGameII();
        int[] arr = {1,1,1,1};
        
        int jumps = test.jumpII(arr);
    }
    public int jumpII(int[] nums) {
        int reachable = nums[0], reached = 0, minSteps = 0;
        
        for(int i = 1; i < nums.length; i++) {
            if(i > reached) {
                minSteps++;
                reached = reachable;
                
                if(reached >= nums.length)
                    return minSteps;
            }
        }
        return minSteps;
    }
}
