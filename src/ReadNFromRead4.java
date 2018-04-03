public class ReadNFromRead4 {
    public static void main(String[] args) {
    
    }
    
    public int read4(char[] buf) {
        return buf.length;
    }
    
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        boolean endOfFile = false;
        int readChars = 0;
        
        while(readChars < n && !endOfFile) {
            int readFour = read4(buffer);
            
            if (readFour < 4) endOfFile = true;
            int len = Math.min(n - readChars, readFour);
            
            for (int i = 0; i < len; i++)
                buf[readChars + i] = buffer[i];
            
            readChars += len;
        }
        return readChars;
    }
}
