import java.util.ArrayList;
import java.util.List;

/**
 * @author louishu
 * @date 1/5/26 14:42
 * @description
 */
public class RecursiveAlgo {


    public static void possibleCombinations(int n) {
        List<List<Integer>> lists = new ArrayList<>();
//        dfs(n, n, new ArrayList<>(), lists);
        dfs2(n, n, 1, new ArrayList<>(), lists);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

    }

    public static void dfs(int remain, int max, List<Integer> list, List<List<Integer>> lists) {
        if (remain == 0) {
            // can't use list, because it will overwrite previous lists
            //lists.add(list);
            // must use a new list
            // end
            lists.add(new ArrayList<>(list));
            // exit
            return;
        }

        int end = (remain > max) ? max : remain;

        for (int i = 1; i <= end; i++) {
            list.add(i);
            // trigger itself
            dfs(remain - i, i, list, lists);
            // backward
            list.remove(list.size() - 1);
        }
    }

    /**
     * @param n
     * @param remain
     * @param pos    the current processing position
     * @param list
     * @param lists
     */
    public static void dfs2(int n, int remain, int pos, List<Integer> list, List<List<Integer>> lists) {
        // finished the last digit
        if (pos == n) {
            // end
            if (remain == 0) {
                lists.add(new ArrayList<>(list));
            }
            //exit
            // can't in if, because current epoch should be ended whether remain = 0
            return;
        }

        if (remain < 0) {
            return;
        }

        for (int i = 0; i <= remain; i++) {
            list.add(i);
            dfs2(n, remain - i, pos + 1, list, lists);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 55. Jump Game - Medium
     * 01/08/2026
     *
     * @param nums
     * @return
     */
    public static boolean canJumpOptimize(int[] nums) {
        // only care about the farest position we can achieve
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        Boolean memo[] = new Boolean[n];
        return dfs(0, n, nums, memo);
    }

    public static boolean dfs(int pos, int n, int[] nums, Boolean[] memo) {
        // reached the end
        if (pos >= n - 1) {
            //exit
            return true;
        }
        if (pos != n - 1 && nums[pos] == 0)
            return false;

        // means pos can reach the end
        if (memo[pos] != null) return memo[pos];

        int furthest = Math.min(pos + nums[pos], n - 1);
        // start with next pos
        for (int i = pos + 1; i <= furthest; i++) {
            if (dfs(i, n, nums, memo) == true) {
                // can reach the final
                memo[pos] = true;
                return true;
            }
        }
        // default value
        memo[pos] = false;
        return false;
    }

    /**
     * 70. Climbing Stairs - Easy
     * 01/09/2026
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return dfsforclimb(0, n, memo);
    }

    public static int dfsforclimb(int pos, int n, int[] memo) {
        if (pos == n) {
            return 1;
        }
        if (pos > n) return 0;


        int end = Math.min(n - pos, 2);
        int ways = 0;

        for (int i = 1; i <= end; i++) {
            if (memo[pos + i] != 0)
                ways += memo[pos+i];
            else
                ways += dfsforclimb(pos + i, n, memo);
        }
        memo[pos] = ways;
        return ways;
    }
    public static int climbStairsDP(int n) {
        int[] dp = new int[n + 2];
        dp[n] = 1;
        dp[n + 1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 1, 4};
        System.out.println(climbStairs(44));
    }
}
