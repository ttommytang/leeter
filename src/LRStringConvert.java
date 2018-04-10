public class LRStringConvert {
    public static void main(String[] args) {

    }

    public boolean canTransform(String start, String end) {
        if (start == null || end == null) return false;
        int indexS = 0, indexE = 0;

        while(true) {
            while(indexE < end.length() && end.charAt(indexE) == 'X') indexE++;
            while(indexS < start.length() && start.charAt(indexS) == 'X') indexS++;

            if (indexE == end.length() && indexS == start.length()) return true;
            if (indexE == end.length() || indexS == start.length()) return false;

            if (start.charAt(indexS) != end.charAt(indexE)) return false;
            if (start.charAt(indexS) == 'L' && indexE > indexS) return false;
            if (start.charAt(indexS) == 'R' && indexE < indexS) return false;
            indexS++; indexE++;
        }
    }
}
