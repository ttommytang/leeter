public class MaxNumOfChunksToSorted {
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        int[] maxOnLeft = new int[arr.length];
        int[] minOnRight = new int[arr.length];
        
        maxOnLeft[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxOnLeft[i] = Math.max(maxOnLeft[i-1], arr[i]);
        }
        
        minOnRight[arr.length - 1] = arr[arr.length - 1];
        
        for (int j = arr.length - 2; j >= 0; j--) {
            minOnRight[j] = Math.min(minOnRight[j+1], arr[j]);
        }
        
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (maxOnLeft[i] <= minOnRight[i])
                count++;
        }
        
        return count + 1;
    }
}
