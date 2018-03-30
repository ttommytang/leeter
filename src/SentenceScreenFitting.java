import java.util.*;

public class SentenceScreenFitting {
    public static void main(String[] args) {
        SentenceScreenFitting test = new SentenceScreenFitting();

        String[] s1 = {"hello", "world"};
        String[] s2 = {"a", "bcd", "e"};
        String[] s3 = {"I", "had", "apple", "pie"};

        int t1 = test.wordsTyping(s1, 2, 8);
        int t2 = test.wordsTyping(s2, 3, 6);
        int t3 = test.wordsTyping(s3, 4, 5);
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        if (sentence == null || sentence.length == 0) return 0;
        StringBuilder whole = new StringBuilder();

        for (String word : sentence)
            whole.append(word).append(' ');
        int chunkLen = whole.length();
        int start = 0;
        for (int row = 0; row < rows; row++) {
            start += cols;

            if (whole.charAt(start % chunkLen) == ' ')
                start += 1;
            else {
                while(start > 0 && whole.charAt((start - 1) % chunkLen) != ' ') start--;
            }
        }
        return start / chunkLen;
    }
}
