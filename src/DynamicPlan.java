import java.lang.reflect.Array;
import java.util.*;

/**
 * @author louishu
 * @date 1/9/26 15:22
 * @description
 */
public class DynamicPlan {

    /**
     * 198. House Robber - Medium
     *
     * @Date 01/09/2026
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        int memo[] = new int[n];
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int tmp = nums[i] + nums[i - 2];
            memo[i] = Math.max(memo[i - 1], tmp);
        }
        return memo[n - 1];
    }

    /**
     * 139. Word Break - Medium
     *
     * @Date: 01/09/2026
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            // target word
            String str = wordDict.get(index);
            if (i + str.length() - 1 > s.length())
                return false;
            if (s.substring(i, i + str.length()).equals(wordDict.get(index))) {
                index++;
                i += str.length() - 1;
            }
            if (index == wordDict.size()) {
                return true;
            }
        }
        if (index == wordDict.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 322. Coin Exchange - Medium
     *
     * @Date 01/09/2026
     */
    public int coinChangeDP(int[] coins, int amount) {
        // store the result in dp[amount]
        int dp[] = new int[amount + 1];

        // set the initialize amount. +1 is to avoid the case that minimum amount = amount
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // amount start at 1
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // has remains
                if (i - coin >= 0) {
                    // dp[i]: the minimum number to achieve the amount
                    // dp[i - coin]: after use the coin, the minimum number to achieve the remain amount
                    // +1: add the current coin
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // if the result is still the maximum amount (no solution tag), return -1
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        // the minimum number of coins in index i to achieve the amount
        int memo[][] = new int[amount + 1][coins.length];
        int res = coinDfs(coins, amount, coins.length - 1, memo);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * @param coins  array
     * @param amount remain amount
     * @param index  the current processing coin index
     * @return the coins amount
     */
    public static int coinDfs(int[] coins, int amount, int index, int[][] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0 || index < 0) return Integer.MAX_VALUE;
        if (memo[amount][index] != 0) return memo[amount][index];

        int min = Integer.MAX_VALUE;
        int end = amount / coins[index];

        for (int i = end; i >= 0; i--) {
            int n = coinDfs(coins, amount - i * coins[index], index - 1, memo);
            if (n != Integer.MAX_VALUE) {
                // the minimum coins amount
                min = Math.min(min, n + i);
            }
        }
        // save the result
        memo[amount][index] = min;
        return min;
    }


    /**
     * 300. Longest Increasing Subsequence - Medium
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int maxL = 1;
        // the max increasing length
        int dp[] = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxL = Math.max(maxL, dp[i]);
        }
        return maxL;
    }

    /**
     * @param costs
     * @param capacity
     * @param budget
     * @return
     */
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        int[] lumarexano = costs;

        int[][] machines = new int[n][2];
        for (int i = 0; i < n; i++) {
            machines[i][0] = costs[i];     // Cost
            machines[i][1] = capacity[i];  // Capacity
        }

        Arrays.sort(machines, (a, b) -> Integer.compare(a[0], b[0]));

        int[] sortedCosts = new int[n];
        int[] sortedCapacity = new int[n];
        int[] maxCapacityPrefix = new int[n];

        for (int i = 0; i < n; i++) {
            sortedCosts[i] = machines[i][0];
            sortedCapacity[i] = machines[i][1];

            if (i == 0) {
                maxCapacityPrefix[i] = sortedCapacity[i];
            } else {
                maxCapacityPrefix[i] = Math.max(maxCapacityPrefix[i - 1], sortedCapacity[i]);
            }
        }

        long maxTotalCapacity = 0;

        for (int i = 0; i < n; i++) {
            int currentCost = sortedCosts[i];
            int currentCap = sortedCapacity[i];

            if (currentCost < budget) {
                maxTotalCapacity = Math.max(maxTotalCapacity, currentCap);
            } else {
                break;
            }

            int target = budget - currentCost;

            int low = 0;
            int high = i;

            while (low < high) {
                int mid = low + (high - low) / 2;
                if (sortedCosts[mid] >= target) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            if (low > 0) {
                long pairCapacity = (long) currentCap + maxCapacityPrefix[low - 1];
                maxTotalCapacity = Math.max(maxTotalCapacity, pairCapacity);
            }
        }

        return (int) maxTotalCapacity;
    }

    /**
     * 91. Decode Ways - Medium
     *
     * @param s
     * @return
     * @Date 01/27/2026
     */
    public int numDecodings(String s) {
        int dp[] = new int[3];
        int n = s.length();
        dp[0] = 1;
        s = ' ' + s;
        for (int i = 1; i <= n; i++) {
            // check the single digit validation
            int single = s.charAt(i) - '0';
            // check the dual digit validation
            int dual = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            // reset everytime for adding the new result
            dp[i % 3] = 0;
            if (single >= 1 && single <= 9)
                dp[i % 3] += dp[(i - 1) % 3];
            // both single and dual are possible, so here using parallel if
            if (dual >= 10 && dual <= 26)
                dp[i % 3] += dp[(i - 2) % 3];
        }
        return dp[n % 3];
    }

    /**
     * 45. Jump Game II - Medium
     *
     * @param nums
     * @return
     * @Date - 02/01/2026
     */
    public int jump(int[] nums) {
        int step[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            step[i] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            int end = Math.min(nums.length - 1, i + nums[i]);
            for (int j = i + 1; j <= end; j++) {
                step[j] = Math.min(step[j], step[i] + 1);

            }
        }
        return step[nums.length - 1];
    }

    public int jumpGreedy(int[] nums) {
        int step = 0;
        int maxPos = 0;
        int curEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // the farest point that the current point can reach
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == curEnd) {
                step++;
                curEnd = maxPos;
            }
        }
        return step;
    }

    /**
     * 72. Edit Distance - Medium
     *
     * @param word1
     * @param word2
     * @return
     * @Date 02/18/2026
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int dp[][] = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            char a = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char b = word2.charAt(j - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // delete word 1
                    int delete = dp[i - 1][j] + 1;
                    // add in word 2
                    // add and delete are reversible
                    int add = dp[i][j - 1] + 1;
                    // replace the current one
                    int replace = dp[i - 1][j - 1] + 1;
                    int min = Math.min(add, delete);
                    dp[i][j] = Math.min(min, replace);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Q85. Maximal Rectangle - Hard
     *
     * @param matrix
     * @return
     * @Date 02/19/2026
     */
    public int maximalRectangle(char[][] matrix) {
        int width = matrix[0].length;
        int height = matrix.length;
        int h[] = new int[width];
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;

        for (int i = 0; i < height; i++) {
            // update height
            stack.clear();
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == '0')
                    h[j] = 0;
                else h[j]++;
            }

            int leftMin[] = new int[width];
            int rightMin[] = new int[width];
            leftMin[0] = -1;
            rightMin[width - 1] = width;

            for (int j = 0; j < width; j++) {
                while (!stack.isEmpty() && h[stack.peek()] >= h[j])
                    stack.pop();

                leftMin[j] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(j);
            }
            stack.clear();
            for (int j = width - 1; j >= 0; j--) {
                while (!stack.isEmpty() && h[stack.peek()] >= h[j])
                    stack.pop();
                rightMin[j] = stack.isEmpty() ? width : stack.peek();
                stack.push(j);
            }
            for (int j = 0; j < width; j++) {
                max = Math.max(max, h[j] * (rightMin[j] - leftMin[j] - 1));
            }
        }
        return max;
    }


    /**
     * 44. Wildcard Matching
     *
     * @param s
     * @param p
     * @return
     * @Date 02/19/2026
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean dp[][] = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            if (p.charAt(i-1) =='*' && dp[0][i-1])
            dp[0][i] = true;
        }

        for (int i = 1; i <= m; i++) {
            char a = s.charAt(i-1);
            for (int j = 1; j <= n; j++) {
                char b = p.charAt(j-1);
                if (a == b || b == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (b == '*') {
                    // dp[i - 1][j]: delete one digit in s
                    // dp[i][j - 1]: keep the current digit in s, follow up the previous status in p
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
