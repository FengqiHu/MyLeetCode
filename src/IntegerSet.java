import java.lang.reflect.Array;
import java.util.*;

public class IntegerSet {
    public int[] twoSumBasic(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int dif = target - nums[i];
            if (map.containsKey(dif) && map.get(dif) != i) {
                return new int[]{i, map.get(dif)};
            }
        }
        return null;
    }

    /**
     * 7. Reverse Integer
     * 01/03/2026
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int temp = x % 10;
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            result = result * 10 + temp;
            x /= 10;
            Character s = '2';
            Integer.parseInt(s.toString());
        }
        return result;
    }

    /**
     * 12. Integer to Roman - Medium
     * 01/03/2026
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        String str = "";
        if (num / 1000 > 0) {
            int t = num / 1000;
            for (int i = 0; i < t; i++) {
                str = str + 'M';
            }
            num %= 1000;

        }
        if (num / 100 > 0) {
            int h = num / 100;
            if (h == 9) {
                str = str + "CM";
            } else if (h == 4) {
                str = str + "CD";
            } else if (h < 4) {
                for (int i = 0; i < h; i++) {
                    str = str + 'C';
                }
            } else {
                str = str + 'D';
                for (int i = 0; i < h - 5; i++) {
                    str = str + 'C';
                }
            }
            num %= 100;
        }
        if (num / 10 > 0) {
            int t = num / 10;
            if (t == 9) {
                str = str + "XC";
            } else if (t == 4) {
                str = str + "XL";
            } else if (t < 4) {
                for (int i = 0; i < t; i++) {
                    str = str + 'X';
                }
            } else {
                str = str + 'L';
                for (int i = 0; i < t - 5; i++) {
                    str = str + 'X';
                }
            }
            num %= 10;
        }
        if (num == 9) {
            str = str + "IX";
        } else if (num == 4) {
            str = str + "IV";
        } else if (num < 4) {
            for (int i = 0; i < num; i++) {
                str = str + 'I';
            }
        } else {
            str = str + 'V';
            for (int i = 0; i < num - 5; i++) {
                str = str + 'I';
            }
        }

        return str;
    }

    public static String intToRomanDemo(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }


    /**
     * 15. 3Sum - Medium
     * 01/03/2026
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    right--;
                    while (nums[left] == nums[left + 1] && left < right) {
                        left++;
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 16. 3Sum Closest - Medium
     * 01/03/2026
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int mindiff = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = sum - target;
                if (diff < 0) {
                    left++;
                } else if (diff > 0) {
                    right--;
                } else {
                    return sum;
                }
                if (Math.abs(diff) < Math.abs(mindiff)) {
                    mindiff = diff;
                    result = sum;
                }
            }
        }
        return result;
    }


    /**
     * 17. Letter Combinations of a Phone Number - Medium
     * 01/03/2026
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        String str[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();

        // loop the digits, up to 4 times
        for (int i = 0; i < digits.length(); i++) {
            int num = Integer.parseInt(String.valueOf(digits.charAt(i)));
            int times = result.size();

            // loop the digits number of the string
            // first time, directly insert number
            if (result.isEmpty()) {
                for (int k = 0; k < str[num - 2].length(); k++) {
                    result.add(String.valueOf(str[num - 2].charAt(k)));
                }
                continue;
            }
            for (int j = 0; j < times; j++) {
                for (int k = 0; k < str[num - 2].length(); k++) {
                    result.add(result.get(j) + str[num - 2].charAt(k));
                }
            }
            for (int l = 0; l < times; l++) {
                result.remove(0);
            }

        }
        return result;
    }

    /**
     * 27. Remove Duplicates - Easy
     * 01/04/2026
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                // slow records the unique number index
                // fast detects future numbers
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }


    /**
     * 36. Valid Sudoku - Medium
     * 01/05/2026
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i = i + 3) {
            for (int j = 0; j < 8; j = j + 3) {
                // fix the corner starter
                List<Character> list = new ArrayList<>();
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        if (board[k][l] == '.') {
                            if (list.contains(board[k][l])) {
                                return false;
                            } else {
                                list.add(board[k][l]);
                            }
                        }
                    }
                }
            }
        }
        // check  row
        for (int i = 0; i < 9; i++) {
            List<Character> list = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    if (list.contains(board[i][j])) {
                        return false;
                    } else {
                        list.add(board[i][j]);
                    }
                }
            }
        }
        // check  column
        for (int i = 0; i < 9; i++) {
            List<Character> list = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') {
                    if (list.contains(board[j][i])) {
                        return false;
                    } else {
                        list.add(board[j][i]);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 202. Happy Number - Easy
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {

        if (n == 1) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        list.add(n);
        while (n > 0) {
            int sum = 0;
            // get the sum of each number
            while (n > 0) {
                int temp = n % 10;
                sum += temp * temp;
                n = n / 10;
            }

            if (list.contains(sum)) {
                return false;
            }
            if (sum == 1) {
                return true;
            } else {
                n = sum;
                list.add(n);
            }
        }
        return false;
    }

    /**
     * 121. Best Time to Buy and Sell Stock - Easy
     *
     * @param prices
     * @return
     * @Date 01/10/2026
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int max = 0, buyPrice = prices[0];
        for (int i = 1; i < n; i++) {
            // scan the best price to buy and calculate the profit
            if (prices[i] < buyPrice) {
                buyPrice = prices[i];
            }
            max = Math.max(max, prices[i] - buyPrice);
        }
        return max;
    }


    /**
     * 169. Majority Element - Easy
     *
     * @param nums
     * @return
     * @Date 01/10/2026
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxValue = 1, maxIndex = nums[0];
        for (int i : nums) {
            map.put(i, 1 + map.getOrDefault(i, 0));

            if (maxValue < map.get(i) + 1) {
                maxValue = map.get(i) + 1;
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    /**
     * 128. Longest Consecutive Sequence - Medium
     *
     * @param nums
     * @return
     * @Date 01/11/2026
     */
    public int longestConsecutive(int[] nums) {
        int max = 1, res = 0;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        Arrays.sort(nums);
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        for (int i = 0; i < list.size(); i++) {
            // get the ith key
            int current = list.get(i);
            if (i < list.size() - 1 && current + 1 == list.get(i + 1)) {
                max++;
            } else {
                res = Math.max(res, max);
                max = 1;
            }
        }
        return res;
    }

    /**
     * 88. Merge Sorted Array - Easy
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @Date 01/11/2026
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int sum = m + n;
        for (int i = m; i < sum; i++) {
            nums1[i] = nums2[i - m];
        }
        Arrays.sort(nums1);
    }

    /**
     * 167. Two Sum II - Input Array Is Sorted - Medium
     *
     * @param numbers
     * @param target
     * @return
     * @Date 01/11/2026
     */
    public int[] twoSum167(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        int res[] = new int[2];
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    /**
     * 18. 4Sum - Medium
     *
     * @param nums
     * @param target
     * @return
     * @Date - 01/21/2026
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        sumFour(nums, target, 0, 0, res, new ArrayList<>());
        return res;
    }

    private void sumFour(int[] nums, double target, int count, int start, List<List<Integer>> res, List<Integer> path) {
        if (count == 2) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> tmp = new ArrayList<>(path);
                    tmp.add(nums[left]);
                    tmp.add(nums[right]);
                    res.add(tmp);
                    left++;
                    right--;
                    // prevent duplicate
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
            return;
        }
        for (int i = start; i < nums.length - count; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            sumFour(nums, target - nums[i], count + 1, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 4. Median of Two Sorted Arrays - Hard
     *
     * @param nums1
     * @param nums2
     * @return
     * @Date - 01/24/2026
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num[] = new int[nums1.length + nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            num[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            num[nums1.length + i] = nums2[i];
        }

        Arrays.sort(num);
        if (num.length % 2 == 0) {
            return (num[num.length / 2] + num[num.length / 2 - 1]) / 2.0;
        } else {
            return num[num.length / 2];
        }
    }

    /**
     * 34. Find First and Last Position of Element in Sorted Array - Medium
     *
     * @param nums
     * @param target
     * @return
     * @Date 01/24/2026
     */
    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                start = mid;
                break;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (start == -1) {
            return new int[]{-1, -1};
        } else {
            left = start;
            right = start;
            while (left > 0) {
                if (nums[left - 1] != target) {
                    break;
                }
                left--;
            }
            while (right < nums.length - 1) {
                if (nums[right + 1] != target) {
                    break;
                }
                right++;
            }
            return new int[]{left, right};
        }
    }

    /**
     * 53. Maximum Subarray - Medium
     *
     * @param nums
     * @return
     * @Date - 01/24/2026
     */
    public int maxSubArray(int[] nums) {
        int sum[] = new int[nums.length];
        sum[0] = nums[0];
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        //min stores the minimum prefix
        int min = 0, max = sum[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, sum[i] - min);
            if (sum[i] < min)
                min = sum[i];
        }
        return max;
    }

    /**
     * 56. Merge Intervals - Medium
     *
     * @param intervals
     * @return
     * @Date - 01/24/2026
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        int[] pre = intervals[0];


        for (int i = 1; i < n; i++) {
            if (intervals[i][0] <= pre[1]) {
                // compare the end, [1,4][2,3]
                pre[1] = Math.max(pre[1], intervals[i][1]);
                // set the end
                res.set(res.size() - 1, pre);
            } else {
                res.add(intervals[i]);
                pre = intervals[i];
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 57. Insert Interval - Medium
     *
     * @param intervals
     * @param newInterval
     * @return
     * @Date - 01/24/2026
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
        intervalList.add(newInterval);
        Collections.sort(intervalList, (a, b) -> Integer.compare(a[0], b[0]));

        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        List<int[]> res = new ArrayList<>();
        res.add(intervalList.get(0));

        int[] pre = intervalList.get(0);


        for (int i = 1; i < intervalList.size(); i++) {
            System.out.println("pre:" + pre[0] + " " + pre[1]);
            int[] interval = intervalList.get(i);
            if (interval[0] <= pre[1]) {
                // compare the end, [1,4][2,3]
                pre[1] = Math.max(pre[1], interval[1]);
                res.set(res.size() - 1, pre);
            } else {
                res.add(interval);
                pre = interval;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 66. Plus One - Easy
     *
     * @param digits
     * @return
     * @Date - 01/25/2026
     */
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1]++;
        int i = digits.length - 1;
        System.out.println();
        while (i > 0 && digits[i] == 10) {
            digits[i] = 0;
            digits[i - 1]++;
        }
        if (digits[0] == 10) {
            digits[0] = 0;
            int res[] = new int[digits.length + 1];
            res[0] = 1;
            for (int j = 0; j < digits.length; j++) {
                res[j + 1] = digits[j];
            }
            return res;
        } else {
            return digits;
        }
    }

    /**
     * 67. Add Binary - Easy
     *
     * @param a
     * @param b
     * @return
     * @date 01/25/2026
     */
    public String addBinary(String a, String b) {
        String res = "";
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        // if a or b has number, or has carry
        while (i >= 0 || j >= 0 || carry == 1) {
            // carry represents the current bringing number
            if (i >= 0) {
                carry += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                carry += b.charAt(j) - '0';
                j--;
            }
            res = carry % 2 + res;
            // if = 2 -> add 1 to the next
            carry /= 2;
        }
        return res;

    }

    /**
     * 69. Sqrt(x) - Easy
     *
     * @param x
     * @return
     * @Date - 01/25/2026
     * @Date - 01/25/2026
     */
    public int mySqrt(int x) {
        int left = 0, right = x;
        int ans = 0;
        if (x < 2)
            return x;
        while (left <= right) {
            // avoiding overflow
            int mid = left + (right - left) / 2;
            long temp = (long) mid * mid;
            if (temp > x) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 71. Simplify Path - Medium
     *
     * @param path
     * @return
     * @Date - 01/25/2026
     */
    public String simplifyPath(String path) {
        String paths[] = path.split("/");
        for (int i = 0; i < paths.length; i++) {
            System.out.println(paths[i]);
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < paths.length; i++) {
            // other circumstance, ignore
            if (paths[i].equals(".") || paths[i].equals("/")) {
                continue;
            }
            // parent path, back to the previous path
            else if (paths[i].equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else if (paths[i].length() > 0) {
                stack.push(paths[i]);
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        if (res == "") {
            return "/";
        }
        return res;
    }

    /**
     * 75. Sort Colors - Medium
     *
     * @param nums
     * @Date - 01/25/2026
     */
    public void sortColors(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        count.put(0, 0);
        count.put(1, 0);
        count.put(2, 0);

        for (int num : nums) {
            count.put(num, count.get(num) + 1);
        }

        int index = 0;
        for (int i = 0; i < 3; i++) {
            int freq = count.get(i);
            for (int j = 0; j < freq; j++) {
                nums[index] = i;
                index++;
            }
        }
    }

    /**
     * 81. Search in Rotated Sorted Array II - Medium
     *
     * @param nums
     * @param target
     * @return
     * @Date - 01/25/2026
     */
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // make sure right numb has reached the end (most left pos)
        while (left < right && nums[left] == nums[right]) {
            right--;
        }
        // find the start point (left margin)
        while (left < right) {
            // find the left, make the mid-point slide to right
            int mid = left + (right - left) / 2 + 1;
            if (nums[0] <= nums[mid])
                left = mid;
            else
                right = mid - 1;
        }
        // finally, left == right
        int pivot = left;
        if (target >= nums[0]) {
            left = 0;
            right = pivot;
        } else {
            left = pivot;
            right = nums.length - 1;
        }
        while (left < right) {
            // find the left, make the mid-point slide to right
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] == target)
                return true;
            if (nums[mid] <= target)
                left = mid;
            else
                right = mid - 1;
        }
        return nums[left] == target ? true : false;
    }


    /**
     * 41. First Missing Positive - Hard
     *
     * @param nums
     * @return
     * @Date - 01/27/2026
     */
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int target = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                target++;
            } else if (nums[i] > target) {
                return target;
            }
        }
        return target;
    }

    /**
     * 80. Remove Duplicates from Sorted Array II - Medium
     *
     * @param nums
     * @return
     * @Date - 01/29/2026
     */
    public int removeDuplicatesII(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) <= 2) {
                nums[res++] = num;
            }
        }
        return res;
    }

    /**
     * 29. Divide two intergers
     *
     * @param dividend
     * @param divisor
     * @return
     * @Date - 02/01/2026
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        // decide the sign
        int sign = ((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) ? 1 : -1;
        long res = 0;

        while (a >= b) {
            long tmp = b, count = 1;
            while ((tmp * 2) <= a) {
                tmp *= 2;
                count *= 2;
            }
            a -= tmp;
            res += count;
        }

        return (int) (sign * res);
    }

    /**
     * 60. Permutation Sequence - Hard
     *
     * @param n
     * @param k
     * @return
     * @Date - 02/07/2026
     */
    public static String getPermutation(int n, int k) {
        String res = "";
        int fact[] = new int[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        // 0-based
        k--;
        while (list.size() > 0) {
            // begin from fact(n)
            int groupSize = fact[n - 1];
            int pos = k / groupSize;

            res += list.get(pos);
            list.remove(pos);
            // k 在“当前组选中的那一块”里排第几个
            k = k % groupSize;
            n--;
        }
        return res;
    }

    /**
     * 118. Pascal's Triangle
     * @Date - 02/17/2026
     * Happy Chinese New Year!
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        res.add(new ArrayList<>(list));

        for (int i = 1; i < numRows; i++) {
            list.clear();
            list.add(1);
            // i is the number of current row
            List<Integer> before = res.get(i-1);
            for (int j = 1; j <= i-1; j++) {
                list.add(before.get(j) + before.get(j-1));
            }
            list.add(1);
            res.add(new ArrayList<>(list));
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(generate(5));
    }
}
