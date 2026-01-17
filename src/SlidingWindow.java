import java.util.*;

/**
 * @author louishu
 * @date 1/15/26 18:10
 * @description
 */
public class SlidingWindow {
    /**
     * 2062. Count Vowel Substrings of a String - Easy
     *
     * @param s
     * @return
     * @Date - 1/15/26 18:10
     */
    public static int countVowelSubstrings(String s) {
        int ans = 0;
        int l = 0;
        int mid = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);

            // not vowel
            if (!isVowel(c)) {
                // move to the next char
                // l: the start index of the vowel substring
                l = r + 1;
                mid = r + 1;
                // reset
                map.clear();
                continue;
            }

            map.put(c, map.getOrDefault(c, 0) + 1);

            // trigger requirement: already has 5 different vowels and now encountered a duplicate first vowel
            // find the max mid that fulfills requirement
            while (map.size() == 5 && map.get(s.charAt(mid)) > 1) {
                // minus the duplicate vowel, find the max mid
                map.put(s.charAt(mid), map.get(s.charAt(mid)) - 1);
                mid++;
            }

            // count substring
            if (map.size() == 5) {
                // first add 1 - substring that has 5 vowels
                // when has duplicate chars, expand the substring
                ans += (mid - l + 1);
            }
        }

        return ans;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    /**
     * 30. Substring with Concatenation of All Words - Hard
     *
     * @param s
     * @param words
     * @return
     * @Date 01/16/2026
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        int n = s.length();
        int wordSize = words[0].length();

        List<String> stringList = new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();

        int sum = 0;
        Arrays.sort(words);
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
            sum += words[i].length();
        }

        for (int i = 0; i <= n - sum; i++) {
            // is one of the start char
            String subString = s.substring(i, i + sum);

            if (isSub(wordSize, subString, map)) {
                res.add(i);

            }
        }
        return res;
    }

    public static boolean isSub(int wordSize, String s, HashMap<String, Integer> words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() / wordSize; i++) {
            String key = s.substring(i * wordSize, (i + 1) * wordSize);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return map.equals(words);
    }

    /**
     * 209. Minimum Size Subarray Sum - Medium
     *
     * @param target
     * @param nums
     * @return
     * @Date - 01/16/2026
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                }
                sum -= nums[left];
                left++;
            }
        }

        return minLen != Integer.MAX_VALUE ? minLen : 0;
    }

    public static void main(String[] args) {
        String str = "aeiouiae";
        System.out.println(countVowelSubstrings(str));
    }
}
