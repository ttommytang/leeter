public class ScreenSentenceFitting {
    public static void main(String[] args) {
        ScreenSentenceFitting test = new ScreenSentenceFitting();
        
        String[] sent1 = {"hello", "world"};
        String[] sent2 = {"a", "bcd", "e"};
        String[] sent3 = {"I", "had", "apple", "pie"};
        
        int t1 = test.wordsTyping(sent1, 2, 8);
        int t2 = test.wordsTyping(sent2, 3, 6);
        int t3 = test.wordsTyping(sent3, 4, 5);
    }
    
    public int wordsTyping(String[] sentence, int rows, int cols) {
        if (sentence == null || sentence.length == 0) return 0;
        
        int usedRows = 0, usedCols = 0, wordCount = 0;
        
        while(true) {
            int wordIndex = wordCount % sentence.length;
            
            if (usedCols == 0) {
                // At head of a new row
                if (usedCols + sentence[wordIndex].length() <= cols) {
                    usedCols = sentence[wordIndex].length();
                    wordCount++;
                }
                else return 0;
            } else {
                // In the middle of a row
                if (usedCols + 1 + sentence[wordIndex].length() <= cols) {
                    usedCols += (1 + sentence[wordIndex].length());
                    wordCount++;
                } else {
                    usedRows++;
                    usedCols = 0;
                }
            }
            
            if (usedRows >= rows) break;
        }
        return wordCount / sentence.length;
    }
}
