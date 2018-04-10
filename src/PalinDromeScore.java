import java.util.*;

public class PalinDromeScore {
    public static int almost_palindromes(String str) {
        if (str == null || str.length() < 2) return 0;
        
        int score = 0, len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1))
                score++;
        }
        return score;
    }
    
    public static int four_letter_words(String sentence) {
        if (sentence == null || sentence.length() == 0) return 0;
        String[] words = sentence.split(" ");
        int count = 0;
        for (String word : words)
            if (word.length() == 4) count++;
        
        return count;
    }
    
    public static int ascii_deletion_distance(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        int sum1 = 0;
        for (char ch : str1.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            sum1 += (int)ch;
        }
        
        int sum2 = 0;
        List<Character> common = new ArrayList<>();
        for (char ch : str2.toCharArray()) {
            sum2 += (int)ch;
            if (map.containsKey(ch) && map.get(ch) > 0) {
                common.add(ch);
                map.put(ch, map.get(ch) - 1);
            }
        }
        
        int commonSum = 0;
        for (char ch : common)
            commonSum += (int)ch;
        
        return sum1 + sum2 - 2 * commonSum;
    }
    
    public static int bracket_match(String bracket_string) {
        int count = 0;
        Stack<Character> stack = new Stack<Character>();
        
        for (char ch : bracket_string.toCharArray()) {
            if (ch == ')') {
                if (stack.peek() != '(') {
                    count++;
                } else
                    stack.pop();
            } else
                stack.push(ch);
        }
        
        count += stack.size();
        return count;
    }
}
