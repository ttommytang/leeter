public class StringRotationCheck {
    public boolean rotateString(String A, String B) {
        if(A == null || B == null || A.length() != B.length()) return false;
        
        String concat = A + A;
        return concat.contains(B);
    }
}
