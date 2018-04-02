import java.util.*;

public class TrapRainWater {
    public static void main(String[] args) {
        TrapRainWater test = new TrapRainWater();
        int[] arr1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        int t1 = test.trapTwoArrays(arr1);
    }
    
    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int water = 0, current = 0;
        Stack<Integer> stack = new Stack<>();
        
        while(current < height.length) {
            while(!stack.isEmpty() && height[current] > height[stack.peek()]) {
                // Get the pop out the bottom of current iteration
                int bottom = height[stack.peek()];
                stack.pop();
                
                if (stack.isEmpty()) break;
                int foreEdge = stack.peek();
                water += (current - foreEdge - 1) * (Math.min(height[current], height[foreEdge]) - bottom);
            }
            stack.push(current++);
        }
        return water;
    }
    
    public int trapTwoArrays(int[] height) {
        // More traditional solution, build two pre-maxed array
        if (height == null || height.length < 3) return 0;
        int len = height.length;
        int[] maxLookToLeft  = new int[len]; maxLookToLeft[0] = height[0];
        int[] maxLookToRight = new int[len]; maxLookToRight[len - 1] = height[len - 1];
        
        for (int i = 1; i < len; i++)
            maxLookToLeft[i] = Math.max(height[i], maxLookToLeft[i-1]);
        
        for (int i = len - 2; i >= 0; i--)
            maxLookToRight[i] = Math.max(height[i], maxLookToRight[i+1]);
        
        // Since edge cannot trap water
        int water = 0;
        for (int i = 1; i < len - 1; i++)
            water += Math.min(maxLookToLeft[i], maxLookToRight[i]) - height[i];
        
        return water;
    }
}
