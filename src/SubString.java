import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SubString {
    /**
     * 3. Longest Substring Without Repeating Characters
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int length = 0, start = 0, end = 0, maxlength = 0;

        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            for (end = start; end < s.length(); end++) {
                if (map.containsKey(s.charAt(end))) {
                    map.clear();
                    if (maxlength <= length) {
                        maxlength = length;
                    }
                    length = 0;
                    start++;
                    end = start;
                    break;
                } else {
                    map.put(s.charAt(end), 1);
                    length++;
                }
            }
        }
        if (maxlength <= length) {
            maxlength = length;
        }
        return maxlength;
    }


    /**
     * 6. Zigzag Conversion
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        String[] matrix = new String[numRows];

        for (int i = 0; i < numRows; i++) {
            matrix[i] = "";
        }

        int index = 0, row = 0, flag = -1;
        while (index < s.length()) {
            matrix[row] += s.charAt(index);
            if (row == numRows - 1 || row == 0) {
                flag = -flag;
            }
            if (flag == 1) {
                row++;
            } else {
                row--;
            }
            index++;
        }

        String result = "";
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(matrix[i]);
            result += matrix[i];
        }
        return result;
    }

    /**
     * 8. String to Integer (atoi)
     * @param s
     * @return
     */
    public static int myAtoi(String s) {
        //remove the blankspace in s
        s = s.trim();
        int flag = 1, sign = 1, start = 0, end = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '-' && (i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')) {
                sign = -1;
            }
            if (c >= '0' && c <= '9') {
                start = i;
                break;
            }
        }
        // start at number
        int j = start, isOut = 0;
        for (j = start; j < s.length(); j++) {
            Character c = s.charAt(j);
            if (c >= '0' && c <= '9') {
                System.out.println(result);
                System.out.println("sign: " + sign + ", c :" + (c-'0'));
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && c - '0' >= (sign == 1 ? 7 : 8))) {
                    if (sign == -1) {
                        result = Integer.MIN_VALUE;
                    } else {
                        result = Integer.MAX_VALUE;
                    }
                    isOut = 1;
                    continue;
                }
                if (isOut == 0)
                    result = result * 10 + Integer.parseInt(c.toString());
            } else {
                end = j;
                break;
            }
        }
        if (j == s.length()) {
            end = j - 1;
        }
        if (isOut == 1 && (start - 1 >= 0 && s.charAt(start - 1) == '-')) {
//            result = Integer.MIN_VALUE;
            start--;
        } else if (start > 0 && (s.charAt(start - 1) == '-' || s.charAt(start - 1) == '+')) {
            result = result * sign;
            start--;
        }
        // If the number is out of range OR the number is a pure number OR encountered a sign
        if ((isOut == 1 && (start == 0 || end != s.length() - 1)) || (start == 0 && end == s.length() - 1) || (start == 0 && end > 0)) {
            return result;
        } else if (end == s.length() - 1) {
            return 0;
        }
        return 0;
    }

    /**
     * #5 Longest Palindromic Substring
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        String maxString = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length()-1; j >=i; j--) {
                String sub = s.substring(i, j+1);
                if (isPalindrome(sub)){
                    if (maxString.length()< j-i+1){
                        maxString = sub;
                    }
                    if(maxString.length()>3)
                        return maxString;
                }
            }
        }
        return maxString;
    }

    private static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i)!= s.charAt(s.length()-i-1)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String str = " ";
        System.out.println(longestPalindrome("babad"));
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE);

    }
}
