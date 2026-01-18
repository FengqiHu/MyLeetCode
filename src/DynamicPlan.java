import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
               if (nums[i] > nums[j]){
                   dp[i] = Math.max(dp[i], dp[j] + 1);
               }
            }
            maxL = Math.max(maxL, dp[i]);
        }
        return maxL;
    }

    /**
     *
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
}
