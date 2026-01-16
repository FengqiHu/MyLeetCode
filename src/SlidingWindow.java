import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author louishu
 * @date 1/15/26 18:10
 * @description
 */
public class SlidingWindow {
    /**
     * 2062. Count Vowel Substrings of a String - Easy
     * @Date - 1/15/26 18:10
     * @param s
     * @return
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

            map.put(c, map.getOrDefault(c,0) + 1);

            // trigger requirement: already has 5 different vowels and now encountered a duplicate first vowel
            // find the max mid that fulfills requirement
            while (map.size() == 5 && map.get(s.charAt(mid)) > 1) {
                // minus the duplicate vowel, find the max mid
                map.put(s.charAt(mid), map.get(s.charAt(mid))- 1);
                mid++;
            }

            // count substring
            if (map.size()==5) {
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

    public static void main(String[] args) {
        String str = "aeiouiae";
        System.out.println(countVowelSubstrings(str));
    }
}
