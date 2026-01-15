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
    public static long countVowelSubstrings(String s) {
        long ans = 0;
        int l = 0;
        int mid = 0;
        int vowelTypes = 0;
        int[] counts = new int[128];

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);

            if (!isVowel(c)) {
                l = r + 1;
                mid = r + 1;
                vowelTypes = 0;
                counts = new int[128];
                continue;
            }

            counts[c]++;
            if (counts[c] == 1) {
                vowelTypes++;
            }

            while (vowelTypes == 5 && counts[s.charAt(mid)] > 1) {
                counts[s.charAt(mid)]--;
                mid++;
            }

            if (vowelTypes == 5) {
                ans += (mid - l + 1);
            }
        }

        return ans;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
