import java.util.*;

public class StringSet {
    /**
     * 3. Longest Substring Without Repeating Characters
     *
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
     *
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
     *
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
                System.out.println("sign: " + sign + ", c :" + (c - '0'));
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
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        String maxString = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                String sub = s.substring(i, j + 1);
                if (isPalindrome(sub)) {
                    if (maxString.length() < j - i + 1) {
                        maxString = sub;
                    }
                    if (maxString.length() > 3)
                        return maxString;
                }
            }
        }
        return maxString;
    }

    private static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 28. find the index of the first occurrence in a string
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        int target = needle.length();
        for (int i = 0; i <= haystack.length() - target; i++) {
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
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            if (map1.containsKey(ransomNote.charAt(i))) {
                map1.put(ransomNote.charAt(i), map1.get(ransomNote.charAt(i)) + 1);
            } else {
                map1.put(ransomNote.charAt(i), 1);
            }
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (map2.containsKey(magazine.charAt(i))) {
                map2.put(magazine.charAt(i), map2.get(magazine.charAt(i)) + 1);
            } else {
                map2.put(magazine.charAt(i), 1);
            }
        }
        for (Character c : map1.keySet()) {
            if (!map2.containsKey(c) || map1.get(c) > map2.get(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 58. Length of Last Word - Easy
     *
     * @param s
     * @return
     * @Date 01/10/2026
     */
    public int lengthOfLastWord(String s) {
        String words[] = s.split(" ");
        return words[words.length - 1].length();
    }

    /**
     * 125. Valid Palindrome - Easy
     *
     * @param s
     * @return
     * @Date 01/10/2026
     */
    public static boolean isPalindrome1(String s) {
        int left = 0, right = s.length() - 1;
        s = s.toLowerCase();

        while (left < right) {
            if (!(Character.isLetter(s.charAt(left)) || Character.isDigit(s.charAt(left)))) {
                left++;
                continue;
            } else if (!(Character.isLetter(s.charAt(right)) || Character.isDigit(s.charAt(right)))) {
                right--;
                continue;
            }
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 49. Group Anagrams - Medium
     *
     * @param strs
     * @return
     * @Date 01/11/2026
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // use sorted string as key
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 68. Text Justification - Hard
     *
     * @param words
     * @param maxWidth
     * @return
     * @Date 01/11/2026
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        // index marks the end word position
        int totalLength = 0, start = 0, end = 0;
        int space[] = new int[maxWidth];
        String str = "";
        for (int i = 0; i < words.length; i++) {
            str = "";
            totalLength = -1;
            Arrays.fill(space, 0);
            while (end < words.length && totalLength + 1 + words[end].length() <= maxWidth) {
                totalLength += words[end].length() + 1;
                end++;
            }
            int spaceSum = maxWidth - totalLength + (end - start - 1);
            int spaceCount = end - start - 1;

            if (end != words.length) {
                if (spaceCount != 0) {
                    for (int j = 0; j < end - start - 1; j++) {
                        space[j] = spaceSum / spaceCount;
                    }
                    for (int j = 0; j < spaceSum % spaceCount; j++) {
                        space[j]++;
                    }
                } else {
                    space[0] = spaceSum;
                }
            } else {
                // the last sentence, need to add space to the end
                for (int j = 0; j < end - start - 1; j++) {
                    space[j]++;
                }
                // giving the rest space to the last one
                space[end - start - 1] = spaceSum - (end - start - 1);
            }

            // calculate the plan for adding space
            for (int j = start; j < end; j++) {
                str += words[j];
                if (space[j - start] != 0) {
                    str += " ".repeat(space[j - start]);

                }
            }
            start = end;
            i = end - 1;
            res.add(str);
        }
        return res;
    }

    /**
     * 392. Is Subsequence - Easy
     * @Date 01/11/2026
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        // tract s
        int j = 0;
        if (s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            while (j < t.length()) {
                if (i < s.length() && s.charAt(i) == t.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == s.length()) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String str = " ";
        str += "s".repeat(-1);

        System.out.println(str);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE);

    }
}
