import java.util.*;

public class NumToLetter {
    public static void main(String[] args) {
        NumToLetter test = new NumToLetter();
        List<String> res = test.letterCombinations("23");
    }

    static char[][] map = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        StringBuilder cur = new StringBuilder();
        manipulateCombs(digits, 0, res, cur);

        return res;
    }

    private void manipulateCombs(String digits, int at, List<String> res, StringBuilder current) {
        if (at >= digits.length()) {
            res.add(current.toString());
            return;
        }

        int digit = digits.charAt(at) - '0';
        if (digit > 1 && digit <= 9) {
            for (char candidate : map[digit]) {
                current.append(candidate);
                manipulateCombs(digits, at + 1, res, current);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }
}
