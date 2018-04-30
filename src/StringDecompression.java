import java.util.HashMap;

public class StringDecompression {
    public static void main(String[] args) {
        StringDecompression test = new StringDecompression();
        
        String str1 = "3[a]2[b4[F]c]";
        
        String res1 = test.decompress(str1);
    }
    /**
     * Given a compressed string in which a number followed by [] indicate how many times those characters occur, decompress the string
     * Eg. : a3[b2[c1[d]]]e will be decompressed as abcdcdbcdcdbcdcde.
     * Assume the string is well formed and number will always be followed by a [].
     */
    public String decompress(String encrypted) {
        if (encrypted == null || encrypted.length() == 0) return encrypted;
        
        HashMap<String, String> memo = new HashMap<>();
        
        return decompressUtil(encrypted, 0, encrypted.length(), memo);
    }
    
    private String decompressUtil(String str, int left, int right, HashMap<String, String> memo) {
        String pattern = str.substring(left, right);
        
        if (memo.containsKey(pattern)) return memo.get(pattern);
        
        int i = left;
        StringBuilder res = new StringBuilder();
        while(i < right) {
            int count = 0;
            while(i < right && str.charAt(i) != '[') {
                char c = str.charAt(i);
                if (Character.isLetter(c))
                    res.append(str.charAt(i));
                else if (Character.isDigit(c)) {
                    count *= 10;
                    count += (c - '0');
                }
                i++;
            }
            
            int subPatternLeft = ++i;
            if (subPatternLeft >= right) break;
            
            int brackets = 1;
            while (i < right) {
                if (str.charAt(i) == '[')
                    brackets++;
                else if (str.charAt(i) == ']') {
                    brackets--;
                    if (brackets == 0)
                        break;
                }
                i++;
            }
            
            if (i > subPatternLeft) {
                String decompressed = decompressUtil(str, subPatternLeft, i, memo);
                while(count > 0) {
                    res.append(decompressed);
                    count--;
                }
            }
            
            i++;
        }
        
        memo.put(str.substring(left, right), res.toString());
        return res.toString();
    }
}
