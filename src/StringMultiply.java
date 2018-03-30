public class StringMultiply {
    public String multiply(String num1, String num2) {
        // Assume num1 and num2 are neither null or empty
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        if (num1.equals("0") || num2.equals("0")) return "0";

        StringBuilder res = new StringBuilder();
        int carry = 0, indexOfDigit = 0, digitProd = 0, len1 = num1.length(), len2 = num2.length();

        while(indexOfDigit < len1 +len2) {
            digitProd = carry;

            for (int i = 0; i <= indexOfDigit; i++) {
                if (i >= len1 || (indexOfDigit - i) >= len2) continue;
                int digit1 = num1.charAt(len1 - i - 1) - '0';
                int digit2 = num2.charAt(len2 - indexOfDigit + i - 1) - '0';

                digitProd += (digit1 * digit2);
            }

            carry = digitProd / 10;
            res.append(digitProd % 10);
            indexOfDigit++;
        }

        res.reverse();
        int zeros = 0;
        while(res.charAt(zeros) == '0') zeros++;
        return res.substring(zeros);
    }
}
