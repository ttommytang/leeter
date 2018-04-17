import java.util.*;
import java.util.LinkedList;

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
    
    private Queue<Character> leftFromLastRead = new LinkedList<>();
    
    public int readM(char[] buf, int n) {
        int realRead = 0;
        
        // First read from the left over ->
        if (!leftFromLastRead.isEmpty()) {
            int readFromLeft = Math.min(leftFromLastRead.size(), n);
            
            for (int i = 0; i < readFromLeft; i++)
                buf[realRead++] = leftFromLastRead.poll();
        }
        
        // Check if already read n
        if (realRead >= n) return n;
        
        // Read more using read4
        char[] buffer = new char[4];
        boolean fileEnd = false;
        
        while(realRead < n && !fileEnd) {
            // Read a 4-length chunk
            int read4Res = read4(buffer);
            
            // Mark file end if read in less than 4 chars
            if (read4Res < 4) fileEnd = true;
            
            // Make sure not exceeding the valid chunk length or the needed length
            int toReadThisTime = Math.min(read4Res, n - realRead);
            
            // Read into buf from buffer
            for (int i = 0; i < toReadThisTime; i++)
                buf[realRead++] = buffer[i];
            
            // Store the left un-read chunk
            while (toReadThisTime < read4Res)
                leftFromLastRead.offer(buffer[toReadThisTime++]);
        }
        
        return realRead;
        
    }
}
