public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays test = new DecodeWays();
        
        
    }
    
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        // write your code here
        if (s == null || s.length() == 0) return 0;
    
        int len = s.length();
        int[] counts = new int[len + 1];
        counts[0] = 1; counts[1] = s.charAt(0) == '0' ? 0 : 1;
    
        for (int i = 2; i <= len; i++) {
            // i means using the first i chars in s -> [0, i-1]
            if (s.charAt(i-1) != '0')
                counts[i] = counts[i-1];
            if(s.charAt(i-2) == '0') continue;
            int twoDigits = (s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0');
            if (twoDigits <= 26)
                counts[i] += counts[i-2];
        }
    
        return counts[len];
    }
}
