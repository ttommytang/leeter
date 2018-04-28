public class MinCycleSection {
    public static void main(String[] args) {}
    
    /**
     * @param array: an integer array
     * @return: the length of the minimum cycle section
     */
    public int minimumCycleSection(int[] array) {
        // Write your code here
        if (array == null || array.length == 0) return 0;
        
        // Iterate through all possible length of sections
        int section = 1;
        search: for (; section < array.length; section++) {
            for (int anchor = 0; anchor < section; anchor++) {
                int origin = array[anchor], cursor = anchor + section;
                while(cursor < array.length) {
                    if (array[cursor] == origin) cursor += section;
                    else continue search;
                }
            }
            return section;
        }
        
        return section;
    }
}
