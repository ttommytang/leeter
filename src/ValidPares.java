import java.util.*;

public class ValidPares {
    public static void main(String[] args) {
    
    }
    
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        HashMap<Character, Character> map = new HashMap<>();
        map.put(']', '['); map.put('}', '{'); map.put(')', '(');
    
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '[' || ch == '{' || ch == '(') stack.push(ch);
            else if (ch == ']' || ch == ')' || ch == '}') {
                if (stack.isEmpty()) return false;
                if (stack.pop() != map.get(ch)) return false;
            } else return false;
        }
        return stack.isEmpty();
    }
}
