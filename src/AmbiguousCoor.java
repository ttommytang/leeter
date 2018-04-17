import java.util.*;

public class AmbiguousCoor {
    public static void main(String[] args) {
        AmbiguousCoor test = new AmbiguousCoor();
        String s = "(100)";

        List<String> res = test.ambiguousCoordinates(s);
    }
    public List<String> ambiguousCoordinates(String S) {
        List<String> res = new ArrayList<>();
        if (S == null || S.length() < 3) return res;

        StringBuilder sb = new StringBuilder(S);
        int len = S.length();

        for (int i = 2; i <= len - 2; i++) {
            sb.insert(i, ", ");
            putDecimal(sb, res, i, i+2);
            sb.delete(i, i+2);
        }
        return res;
    }

    // xTo - exclusive, yFrom - inclusive
    private void putDecimal(StringBuilder sb, List<String> res, int xTo, int yFrom) {
        if (xTo < 2 || yFrom > sb.length() - 2) return;

        for (int leftD = 2; leftD <= xTo; leftD++) {
            if (leftD != xTo) {
                sb.insert(leftD, ".");
                xTo++; yFrom++;
            }
            String xStr = sb.substring(1, xTo);

            if (checkDecimal(xStr)) {
                for (int rightD = yFrom + 1; rightD < sb.length(); rightD++) {
                    if (rightD != sb.length() - 1) sb.insert(rightD, ".");

                    String yStr = sb.substring(yFrom, sb.length() - 1);

                    if (checkDecimal(yStr))
                        res.add(sb.toString());
                    if (rightD != sb.length() - 1) sb.deleteCharAt(rightD);
                }
            }
            if (leftD != xTo) {
                sb.deleteCharAt(leftD);
                xTo--; yFrom--;
            }
        }

    }

    private boolean checkDecimal(String str) {
        boolean seenDecimal = false, leadingZero = false;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0' && i == 0) leadingZero = true;
            if (i == 1 && leadingZero && str.charAt(i) != '.') return false;
            if (str.charAt(i) == '.') seenDecimal = true;
            if (seenDecimal && i == str.length() - 1 && str.charAt(i) == '0') return false;
        }
        return true;
    }
}
