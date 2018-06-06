import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringCompress {
    public static void main(String[] args) {
        List<String> strs = Arrays.asList("", "a", "");
        StringCompress test = new StringCompress();

        String encoded = test.encode(strs);
        List<String> decoded = test.decode(encoded);
    }
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null || strs.size() == 0)
            return "";

        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            for (char ch : str.toCharArray()) {
                if (ch == '#')
                    res.append("##");
                else
                    res.append(ch);
            }
            res.append(" # ");
        }

        return res.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;

        String[] strs = s.split(" # ", -1);
        for (int i = 0; i < strs.length - 1; i++) {
            String str = strs[i].replaceAll("##", "#");
            res.add(str);
        }
        return res;
    }
}
