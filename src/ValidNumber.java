public class ValidNumber {
    public static void main(String[] args) {
        ValidNumber test = new ValidNumber();
        
        
    }
    
    public boolean isNumber(String s) {
        s = s.trim();
        boolean sawDec = false, sawE = false, sawDigit = false, hasDigitFollowingE = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                sawDigit = true;
                hasDigitFollowingE = true;
            } else if (ch == '.') {
                // Point cannot happen after another point or e
                if (sawE || sawDec) return false;
                sawDec = true;
            } else if (ch == 'e') {
                // e must follow another number and can only be once
                if (!sawDigit || sawE) return false;
                sawE = true;
                
                // Also need digits to follow e
                hasDigitFollowingE = false;
            } else if (ch == '+' || ch == '-') {
                // +/- must be at head or after e
                if (i != 0 && s.charAt(i-1) != 'e') return false;
            } else return false;
        }
        return sawDigit && hasDigitFollowingE;
    }
}
