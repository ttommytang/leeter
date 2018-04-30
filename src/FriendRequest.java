import java.util.Arrays;

public class FriendRequest {
    public static void main(String[] args) {
        FriendRequest test = new FriendRequest();
        
        int[] arr = {73,106,39,6,26,15,30,100,71,35,46,112,6,60,110};
        
        int req = test.numFriendRequests(arr);
    }
    
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 2) return 0;
        
        Arrays.sort(ages);
        
        System.out.println(ages.toString());
        int count = 0;
        
        for (int i = 0; i < ages.length; i++) {
            int sameAge = 1;
            while(i < ages.length - 1 && ages[i] == ages[i+1]) {
                sameAge++;
                i++;
            }
            
            int left = binarySearch(ages, i - sameAge + 1, (1.0 * ages[i]) * 0.5 + 7);
            
            count += sameAge * (i - sameAge + 1 - left);
            
            if (ages[i] > (1.0 * ages[i]) * 0.5 + 7)
                count += sameAge * (sameAge - 1);
            // i += (sameAge - 1);
            
            int targets = i - sameAge + 1 - left;
            int inner = sameAge * (sameAge - 1);
            
            System.out.println("Age: " + ages[i] + " Nums: " + sameAge + " Targets: " + targets + " Counts: " + count);
        }
        
        return count;
        
        
    }
    
    private int binarySearch(int[] ages, int right, double lowerBound) {
        int left = 0;
        
        while(left < right) {
            int mid = left + (right - left) / 2;
            
            if (ages[mid] > lowerBound) right = mid;
            else left = mid + 1;
        }
        
        return left;
    }
}
