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

    public static int lengthOfLongestSubstringII(String s) {
        int start = 0, end = 0, maxlength = 0;
        Set<Character> map = new HashSet<>();
        int n = s.length();

        if (n == 0)
            return 0;

        while (end < n && start <= end) {
            char c = s.charAt(end);

            while (map.contains(c)) {
                map.remove(s.charAt(start));
                start++;
            }
            map.add(c);
            maxlength = Math.max(maxlength, end - start + 1);
            end++;
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
     *
     * @param s
     * @param t
     * @return
     * @Date 01/11/2026
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

    /**
     * 205. Isomorphic Strings - Easy
     *
     * @param s
     * @param t
     * @return
     * @Date 01/12/2026
     */

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            // marks every character's last position
            if (!mapS.containsKey(s.charAt(i))) {
                mapS.put(s.charAt(i), i);
            }
            if (!mapT.containsKey(t.charAt(i))) {
                mapT.put(t.charAt(i), i);
            }
            if (mapS.get(s.charAt(i)) != mapT.get(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 290. Word Pattern - Easy
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        HashMap<Character, String> map = new HashMap<>();

        if (words.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            // if already has the same character
            char c = pattern.charAt(i);

            if (map.containsKey(c)) {
                // check the word
                if (!map.get(c).equals(words[i])) {
                    return false;
                }
            } else {
                // no mapping
                // the word has already been mapped
                if (map.containsValue(words[i]))
                    return false;
                else
                    map.put(c, words[i]);
            }
        }

        return true;
    }

    /**
     * 10. Regular Expression Matching - Hard
     * 01/23/2026
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean isMatch(String str, String pattern) {
        boolean dp[][] = new boolean[str.length() + 1][pattern.length() + 1];
        // dp[i][j] : str[0...i-1] matches pattern[0...j-1]
        dp[0][0] = true;

        // 2. 初始化: 处理 str 为空，但 pattern 包含 '*' 的情况 ("a*", "a*b*")
        // 这些 pattern 可以匹配空字符串，因为 '*' 可以表示 0 个前面的字符
        for (int j = 1; j <= pattern.length(); j++) {
            if (pattern.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                char s = str.charAt(i - 1);
                char p = pattern.charAt(j - 1);

                if (p != '*') {
                    // just check the two chars
                    if (isCharMatch(s, p)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // eliminate (a*)
                    dp[i][j] = dp[i][j - 2];
                    // ....a...
                    // ...a*...
                    if (isCharMatch(s, pattern.charAt(j - 2))) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j];
                    }
                }

            }
        }
        return dp[str.length()][pattern.length()];
    }

    public boolean isCharMatch(char a, char b) {
        if (a == b || b == '.') {
            return true;
        }
        return false;
    }


    /**
     * 65. Valid Number - Hard
     *
     * @param s
     * @return
     * @Date - 01/30/2026
     */
    public boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                // before must be int or dot, after must be int
                return check(0, i - 1, s, false) && check(i + 1, s.length() - 1, s, true);
            }
        }
        // check the entire sequence
        return check(0, s.length() - 1, s, false);

    }

    public boolean check(int start, int end, String s, boolean isInt) {
        if (s.charAt(start) == '+' || s.charAt(start) == '-')
            start++;
        boolean hasDot = false, hasDigit = false;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == '.') {
                // must be int or has dot before
                if (isInt || hasDot) return false;
                hasDot = true;
            } else if (Character.isDigit(s.charAt(i))) {
                hasDigit = true;
            } else {
                return false;
            }
        }
        return hasDigit;
    }

    public boolean isValidNum(char c) {
        if (Character.isDigit(c) || c == '+' || c == '-' || c == '.' || c == 'e' || c == 'E') {
            return true;
        }
        return false;
    }

    /**
     * 97. Interleaving String - Medium
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     * @Date - 01/29/2026
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();

        if (m + n != s3.length())
            return false;

        s1 = ' ' + s1;
        s2 = ' ' + s2;
        s3 = ' ' + s3;

        // dp[i][j]: the first i chars of s1 and first j chars of s2 can form the first i + j chars of s3
        boolean dp[][] = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // end with s1's end
                if (i > 0 && s3.charAt(i + j) == s1.charAt(i))
                    dp[i][j] = dp[i - 1][j];
                // end with s2's end
                if (j > 0 && s3.charAt(i + j) == s2.charAt(j))
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     * 43. Multiply Strings - Medium
     *
     * @param num1
     * @param num2
     * @return
     * @Date - 01/29/2026
     */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int res[] = new int[m + n + 1];
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        // calculate the sum in each digit
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                res[i + j + 1] += a * b;
            }
        }
        int carry = 0;
        for (int i = res.length - 1; i >= 0; i--) {
            System.out.println("before: " + res[i]);

            int temp = res[i] + carry;
            res[i] = temp % 10;
            carry = temp / 10;
            System.out.println(res[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = res.length - 1; i >= 0; i--) {
            if (res[i] == 0 && i == 0)
                continue;
            sb.append(res[i]);
        }
        return sb.reverse().toString();
    }

    /**
     * 76. Minimum Window Substring - Hard
     *
     * @param s
     * @param t
     * @return
     * @Date - 02/19/2026
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> targetMap = new HashMap<>();
        int start = 0, end = 0;
        int count = 0;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!targetMap.containsKey(c)) {
                end++;
                continue;
            } else {
                map.put(c, map.getOrDefault(c, 0) + 1);
                end++;
                if (targetMap.get(c) <= map.get(c) && s.indexOf(start) == c) {
                    // move to the next valid digit
                    start++;
                    while (!targetMap.containsKey(t.charAt(start))) {
                        start++;
                    }
                }
            }
        }
        System.out.println(start + "," + end);
        String res = s.substring(start, end + 1);
        return res;
    }

    public String minWindowNew(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> targetMap = new HashMap<>();
        int left = 0, right = 0;
        // scan the origin str
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        int nextPos[] = new int[s.length()];
        int pre = 0;
        while (left < right) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                map.put(c, map.getOrDefault(c, 0) + 1);
                nextPos[pre] = right;
                pre = right;
            }
            if (s.charAt(right) == s.charAt(left) && targetMap.get(c) == map.get(c)) {
                map.put(c, map.get(c) - 1);
                left = nextPos[left];
            }
            right++;
        }
        return s.substring(left, right);
    }

    /**
     * 131. Palindrome Partitioning - Medium
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        findPalindromes(s, 0, res, new ArrayList<>());
        return res;

    }

    public void findPalindromes(String s, int start, List<List<String>> lists, List<String> list) {
        if (start == s.length()) {
            lists.add(new ArrayList<>(list));
            System.out.println(list.size());
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            if (isPalindrome(s.substring(start, i))) {
                list.add(s.substring(start, i));
                findPalindromes(s, i, lists, list);
                list.remove(list.size() - 1);
            }
        }

    }

//    public boolean isPalindrome(String s){
//        int n = s.length();
//        for (int i = 0; i < n/2; i++) {
//            if (s.charAt(i)!=s.charAt(n-i-1))
//                return false;
//        }
//        return true;
//    }
}
