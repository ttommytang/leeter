public class DeleteChars {
    public static void main(String[] args) {
        DeleteChars test = new DeleteChars();
        
        assert test.isDeletable("", "");
        assert test.isDeletable("a", "");
        assert !test.isDeletable("", "a");
        assert test.isDeletable("ab", "a");
        assert !test.isDeletable("a", "ab");
        assert test.isDeletable("aebece", "abc");
        assert test.isDeletable("aebec", "abc");
    
    }
    
    public boolean isDeletable(String s, String t) {
        if (t == null || t.length() == 0)
            return true;
        else if (s == null || s.length() == 0)
            return false;
        
        int curS = 0;
        int curT = 0;
        
        while(curS < s.length() && curT < t.length()) {
            if (s.charAt(curS) == t.charAt(curT)) {
                curT++;
            }
            curS++;
        }
        
        return curT == t.length();
    }
}
