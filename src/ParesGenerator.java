import java.util.*;

public class ParesGenerator {
    public static void main(String[] args) {
        ParesGenerator test = new ParesGenerator();
        List<String> res = test.generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;

        StringBuilder cur = new StringBuilder();
        paresManipulate(n * 2, 0, 0, cur ,res);
        return res;
    }

    private void paresManipulate(int n, int left, int right, StringBuilder cur, List<String> res) {
        if (left + right >= n) {
            res.add(cur.toString());
            return;
        }

        if (left < n / 2) {
            cur.append('(');
            paresManipulate(n, left + 1, right, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }

        if (left > right) {
            cur.append(')');
            paresManipulate(n, left, right + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
