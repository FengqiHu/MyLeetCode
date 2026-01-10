import java.util.HashMap;
import java.util.Map;

public class StringSet {
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

    /**
     * 28. find the index of the first occurrence in a string
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        int target = needle.length();
        for (int i = 0; i <= haystack.length()-target; i++) {
            // extract the substring, the second parameter is the end index, not the length
            if (haystack.substring(i, i + target).equals(needle)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 383. Ransom Note - Easy
     * 01/09/2026
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            if (map1.containsKey(ransomNote.charAt(i))){
                map1.put(ransomNote.charAt(i), map1.get(ransomNote.charAt(i))+1);
            } else {
                map1.put(ransomNote.charAt(i), 1);
            }
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (map2.containsKey(magazine.charAt(i))){
                map2.put(magazine.charAt(i), map2.get(magazine.charAt(i))+1);
            } else {
                map2.put(magazine.charAt(i), 1);
            }
        }
        for(Character c: map1.keySet()){
            if (!map2.containsKey(c) || map1.get(c)> map2.get(c)){
                return false;
            }
        }
        return true;
    }

    /**
     * 58. Length of Last Word - Easy
     * @Date 01/10/2026
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        String words[] = s.split(" ");
        return words[words.length-1].length();
    }

    /**
     * 125. Valid Palindrome - Easy
     * @Date 01/10/2026
     * @param s
     * @return
     */
    public static boolean isPalindrome1(String s) {
        String newStr = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))){
                newStr += Character.toLowerCase(s.charAt(i));
            }
        }
        int left = 0, right = newStr.length()-1;

        while(left<right){
            if (newStr.charAt(left)!=newStr.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {
        String str = " ";
        System.out.println(isPalindrome1("A man, a plan, a canal: Panama"));
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE);

    }
}
