import java.util.*;

/**
 * @author louishu
 * @date 1/15/26 14:13
 * @description
 */
public class GreedyAlgo {

    /**
     * Phone Call Problem
     * Giving three arrays, find the optimal subset of non-overlapping phone calls to maximize a total volume.
     * Example:
     * start    = {10, 5, 15, 18, 30};
     * duration = {30, 12, 20, 35, 35};
     * volume   = {50, 51, 20, 25, 10};
     * <p>
     * Output: 76 (Call 2 + Call 4 = 51 + 25)
     * <p>
     * This is local optimal solution
     */
    static class Order {
        int start;
        long end;
        int volume;

        public Order(int start, long end, int volume) {
            this.start = start;
            this.end = end;
            this.volume = volume;
        }
    }

    public static int phoneCalls(int[] start, int[] duration, int[] volume) {

        List<Order> orders = new ArrayList<>();
        int n = start.length;
        if (n == 0) return 0;

        for (int i = 0; i < n; i++) {
            orders.add(new Order(start[i], start[i] + duration[i], volume[i]));
        }

        // sort by the end date
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order a, Order b) {
                return Long.compare(a.end, b.end);
            }
        });

        long[] dp = new long[n];
        dp[0] = orders.get(0).volume;

        for (int i = 1; i < n; i++) {
            // store the previous possible best solution
            long untake = dp[i - 1];
            long take = orders.get(i).volume;

            // choose the closest order, minimum the time margin between orders
            int closest = searchClosest(orders, i);

            if (closest != -1) {
                take += dp[closest];
            }
            dp[i] = Math.max(untake, take);
        }

        return (int) dp[n - 1];
    }

    public static int searchClosest(List<Order> orders, int index) {
        int left = 0, right = index - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (orders.get(mid).end <= orders.get(index).start) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    /**
     * This is global optimal solution, unlike PhoneCall problem.
     */
    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static int backpackProblem(int volume, int weights[], int values[]) {
        int n = weights.length;
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            items.add(new Item(weights[i], values[i]));
        }
//        Collections.sort(items, new Comparator<Item>(){
//            @Override
//            public int compare(Item a, Item b){
//                return Integer.compare(a.weight, b.weight);
//            }
//        });
        // the maximum value can get when volume remains j
        // volume -> value
        int[] dp = new int[volume + 1];

        for (Item item : items) {
            // reverse: avoid reusing the same item
            for (int j = volume; j >= item.weight; j--) {
                dp[j] = Math.max(
                        dp[j],                      // not choose
                        dp[j - item.weight] + item.value  // choose, calculate the new value, if choose the remain volume is [j - item.weight]
                );
            }
        }
        return dp[volume];
    }

    /**
     * 416. Partition Equal Subset Sum - Medium
     *
     * @Date 01/15/2026
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 1; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0)
            return false;
        int target = sum / 2;

        // the maximum value can get when volume remains j
        int dp[] = new int[target + 1];

        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                // choose or not choose
                // j- num : use the volume
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        return (dp[target] == target);
    }

    /**
     * 62. Unique Paths - medium
     *
     * @param m
     * @param n
     * @return
     * @Date - 01/15/2026
     */
    public int uniquePaths(int m, int n) {
        int path[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            path[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            path[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = path[i - 1][j] + path[i][j - 1];
            }

        }
        return path[m - 1][n - 1];

    }

    /**
     * 63. Unique Paths II - medium
     *
     * @return
     * @Date - 01/15/2026
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int path[][] = new int[m][n];
        path[0][0] = 1;

        if (obstacleGrid[0][0] == 1)
            return 0;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1)
                break;
            else
                path[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1)
                break;
            else
                path[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0)
                    path[i][j] = path[i - 1][j] + path[i][j - 1];
            }

        }
        return path[m - 1][n - 1];

    }

    /**
     * 64. Minimum Path Sum - medium
     *
     * @param grid
     * @return
     * @Date - 01/15/2026
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int path[][] = new int[m][n];
        path[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            path[i][0] = path[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            path[0][i] = path[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = Math.min(path[i - 1][j] + grid[i][j], path[i][j - 1] + grid[i][j]);
            }

        }
        return path[m - 1][n - 1];

    }

    /**
     * 376. Wiggle Subsequence
     *
     * @param nums
     * @return
     * @Date 03/07/2026
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        int sum = 1;
        if (n < 2)
            return n;
        int preDiff = 0;
        for (int i = 1; i < n; i++) {
            int curDiff = nums[i] - nums[i - 1];
            if ((preDiff >= 0 && curDiff < 0) || (preDiff <= 0 && curDiff > 0)) {
                sum++;
                preDiff = curDiff;
            }
        }

        return sum;
    }

    /**
     * 122. Best Time to Buy and Sell Stock II
     *
     * @param prices
     * @return
     * @Date 03/07/2026
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            // calculate every day's profit
            int profit = prices[i] - prices[i - 1];
            if (profit > 0)
                maxProfit += profit;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] start = {10, 10, 50, 60, 40};
        int[] duration = {20, 40, 50, 30, 100};
        int[] volume = {10, 20, 30, 40, 50};

        System.out.println(backpackProblem(100, duration, volume));
    }
}
