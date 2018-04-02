import java.util.*;

public class SmallestRotationHighestScore {
    public static void main(String[] args) {
        SmallestRotationHighestScore test = new SmallestRotationHighestScore();
        int[] arr1 = {2, 3, 1, 4, 0};
        int[] arr2 = {1, 3, 0, 2, 4};
        
        int res1 = test.bestRotation(arr1);
        int res2 = test.bestRotation(arr2);
    }
    
    /*
    <-<- rotation direction
    len - length of the array
    V - [2 3 1 4 0]
    I - [0 1 2 3 4]
    
    for the first entry -> V = 2, it has to be rotated to I - [2,4] for one point -> K - [1, 3] not exceeding the len -> adding range
    for the second entry -> V = 3, ==> I - [3, 4] ==> K - [2, 3] ==> not exceeding the range
    for the third entry -> V = 1 ==> I - [1, 4] ==> K - [3, 6] ==> 6 exceed the range -> seperate into [3, 4] & [0, 1]
    ...
    
    ===> generalize to:
    for nums[i] == V -> target range of I -> [V, len - 1] ==> rotation range = [i + 1, i + len - V]
                                                    [rotations to the last index, rotation to the index equal to value]
                        Check if i + len - V >= len, split into [i + 1, len - 1] [0, (i + len - V)%len]
     */
    
    public int bestRotation(int[] A) {
        if (A == null || A.length == 0) return 0;
        
        // Rotation range is [0, A.length - 1] of length A.length
        int len = A.length;
        int[] kScoreBoard = new int[len];
        
        for (int i = 0; i < A.length; i++) {
            int V = A[i], minRotation = (i + 1) % len, maxRotation = i + len - V;
            if (maxRotation < len) {
                kScoreBoard[minRotation]++;
                if (maxRotation + 1 < kScoreBoard.length)
                    kScoreBoard[maxRotation + 1]--;
            } else {
                // When maxRotation exceed the range, we split it into two range
                kScoreBoard[minRotation]++;
                kScoreBoard[0]++;
                if (maxRotation % len + 1 < kScoreBoard.length)
                    kScoreBoard[maxRotation % len + 1]--;
            }
        }
        
        // Now follow the range sum to calculate the max score
        int maxScore = Integer.MIN_VALUE, kScore = 0, resK = 0;
        for (int i = 0; i < kScoreBoard.length; i++) {
            kScore += kScoreBoard[i];
            if (kScore > maxScore) {
                resK = i;
                maxScore = kScore;
            }
        }
        
        return resK;
    }
}
