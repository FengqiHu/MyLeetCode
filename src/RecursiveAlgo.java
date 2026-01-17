import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
                ways += memo[pos + i];
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


    /**
     * 77. Combinations - Medium
     *
     * @param n range [1,n]
     * @param k number of elements
     * @return
     * @Date - 01/16/2026
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        // the first number

        result = combineLists(n, k, new ArrayList<>(), result);

        return result;
    }

    /**
     * @param list   the current processing list
     * @param result add the list to result
     * @return
     */
    public List<List<Integer>> combineLists(int n, int k, List<Integer> list, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(list));
            return result;
        }
        for (int i = n; i >= k; i--) {
            list.add(i);
            combineLists(i - 1, k - 1, list, result);
            list.remove(list.size() - 1);
        }
        return result;
    }

    /**
     * 46. Permutations - Medium
     *
     * @param nums
     * @return
     * @Date 01/17/2026
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteNums(nums, res, new ArrayList<>());
        return res;
    }

    public void permuteNums(int[] nums, List<List<Integer>> res, List<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            permuteNums(nums, res, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 39. Combination Sum - Medium
     *
     * @param candidates
     * @param target
     * @return
     * @Date - 01/17/2026
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationNums(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    private void combinationNums(int[] candidates, int target, List<List<Integer>> res, List<Integer> list, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            // must return, avoid execute target = -2
            return;
        } else if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target > candidates[i]) {
                list.add(candidates[i]);
                combinationNums(candidates, target - candidates[i], res, list, i);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 79. Word Search - Medium
     *
     * @param board
     * @param word
     * @return
     * @Date - 01/17/2026
     */
    public boolean exist(char[][] board, String word) {
        boolean flag = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean visit[][] = new boolean[board.length][board[0].length];
                    flag = wordSearch(i, j, word, board, visit, 0);
                    if (flag)
                        return true;
                }
            }
        }
        return flag;
    }

    public static Boolean wordSearch(int row, int col, String word, char[][] board, boolean visit[][], int start) {
        int rows = board.length;
        int cols = board[0].length;

        // if out of bound / visited / not equal
        if (row >= rows || col >= cols || row < 0 || col < 0 || visit[row][col] || board[row][col] != word.charAt(start)) {
            return false;
        }
        // reached the end
        if (start == word.length() - 1) {
            return true;
        }

        // mark
        visit[row][col] = true;
        boolean flag = (wordSearch(row - 1, col, word, board, visit, start + 1)
                || wordSearch(row + 1, col, word, board, visit, start + 1)
                || wordSearch(row, col - 1, word, board, visit, start + 1)
                || wordSearch(row, col + 1, word, board, visit, start + 1));
        //backtrack
        visit[row][col] = false;
        return flag;
    }


    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 1, 4};
        System.out.println(climbStairs(44));
    }
}
