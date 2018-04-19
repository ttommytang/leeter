public class QuickSelect {
    public static void main(String[] args) {
        QuickSelect test = new QuickSelect();
        
        int[] arr = {3,8,5,2,1};
        
        int s1 = test.findKSmall(arr.clone(), 1);
        int s3 = test.findKSmall(arr.clone(), 3);
    }
    
    // Problem scope : in an unsorted array, find the k-th smallest element
    public int findKSmall(int[] arr, int k) {
        if (arr == null || arr.length < k) return 0;
        
        int left = 0, right = arr.length - 1;
        while(left < right) {
            int pivot = partition(arr, left, right);
            
            if (pivot - left == k - 1) return arr[pivot];
            else if (pivot - left > k - 1) right = pivot - 1;
            else {
                left = pivot + 1;
                k = k - pivot - 1;
            }
        }
        
        return arr[left];
        
    }
    
    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right], i = left;
        
        for (int j = i; j < right; j++) {
            if (arr[j] > pivot) continue;
            swap(arr, i++, j);
        }
        
        swap(arr, i, right);
        return i;
    }
    
    private void swap(int[] arr, int idx1, int idx2) {
        if (idx1 == idx2) return;
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    
}
