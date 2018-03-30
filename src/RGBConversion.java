public class RGBConversion {
    public String similarRGB(String color) {
        if(color == null || color.length() != 7) return "";
        char[] colorCharArray = color.toCharArray();
        
        String res = "#";
        for(int i = 0; i < 3; i++) {
            char high = colorCharArray[2*i + 1];
            char low = colorCharArray[2*(i + 1)];
            
            int highDigit = (high >= '0' && high <= '9') ? high - '0' : high - 'a' + 10;
            int lowDigit = (low >= '0' && low <= '9') ? low - '0' : low - 'a' + 10;
            
            int colorValue = highDigit * 16 + lowDigit;
            
            int mod = colorValue % 17;
            int div = colorValue / 17;
            if (div < 9)
                high = (char) ('0' + div + 1);
            else if (div == 9)
                high = 'a';
            else
                high = (char) ('a' + div - 9);
            
            if (mod >= 9)
                res += high;
            else
                res += high == 'a' ? '9' : (char)(high - 1);
        }
        
        return res;
    }
}
