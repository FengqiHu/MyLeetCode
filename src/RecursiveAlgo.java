import java.lang.reflect.Array;
import java.util.*;

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
     * 47. Permutations II - Medium
     *
     * @param nums
     * @return
     * @Date - 01/17/2026
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // marks the status
        boolean used[] = new boolean[nums.length];
        Arrays.sort(nums);
        permuteNumsUnique(nums, res, new ArrayList<>(), used);
        return res;
    }

    public void permuteNumsUnique(int[] nums, List<List<Integer>> res, List<Integer> list, boolean used[]) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            // skip the same number - this is for the next arrays that not to add the same number
            if (used[i])
                continue;
            // if the same number and had been used
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // mark the current number
            used[i] = true;
            list.add(nums[i]);
            permuteNumsUnique(nums, res, list, used);
            list.remove(list.size() - 1);
            used[i] = false;
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
        if (row >= rows || col >= cols || row < 0 || col < 0 ||
                visit[row][col] ||
                board[row][col] != word.charAt(start)) {
            return false;
        }
        // reached the word's end
        if (start == word.length() - 1) {
            return true;
        }

        // mark visit status
        visit[row][col] = true;
        // if any direction is true, return true
        boolean flag = (wordSearch(row - 1, col, word, board, visit, start + 1)
                //  down
                || wordSearch(row + 1, col, word, board, visit, start + 1)
                // left
                || wordSearch(row, col - 1, word, board, visit, start + 1)
                // right
                || wordSearch(row, col + 1, word, board, visit, start + 1));
        //backtrack
        visit[row][col] = false;
        return flag;
    }

    /**
     * 78. Subsets - Medium
     *
     * @param nums
     * @return
     * @Date - 01/25/2026
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        result.add(list);
        subSetsHelper(nums, 0, list, result);
        return result;
    }

    public static void subSetsHelper(int nums[], int start, List<Integer> list, List<List<Integer>> res) {
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            // make a copy
            res.add(new ArrayList<>(list));

            subSetsHelper(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 90. Subsets II - Medium
     *
     * @param nums
     * @return
     * @Date - 01/26/2026
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        result.add(new ArrayList<>(list));
        subsetsWithDupHelper(nums, 0, list, result);
        return result;
    }

    public static void subsetsWithDupHelper(int nums[], int start, List<Integer> list, List<List<Integer>> res) {
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;
            list.add(nums[i]);
            // make a copy
            res.add(new ArrayList<>(list));

            subsetsWithDupHelper(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }


    /**
     * 93. Restore IP Addresses - Medium
     *
     * @param s
     * @return
     * @Date 01/27/2026
     */
    public List<String> restoreIpAddresses(String s) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int count = 0;
        ipHelper(0, count, res, list, s);
        List<String> ans = new ArrayList<>();
        for (List<Integer> list1 : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list1.size(); i++) {
                sb.append(list1.get(i));
                if (i != list1.size() - 1) {
                    sb.append(".");
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    public static void ipHelper(int start, int count, List<List<Integer>> res, List<Integer> list, String ip) {
        if (count == 4 && start == ip.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        // didn't use all the ip or didn't seg with 4 parts
        if (count == 4 || start == ip.length())
            return;

        int num = 0;
        for (int i = start; i < Math.min(start + 3, ip.length()); i++) {
            char c = ip.charAt(i);

            // start with 0 or exceed the length or not digit and .
            if (i != start && ip.charAt(start) == '0') {
                return;
            }
            num = num * 10 + (c - '0');
            if (num >= 0 && num <= 255) {
                list.add(num);
                ipHelper(i + 1, count + 1, res, list, ip);
                list.remove(list.size() - 1);
            }
        }
    }

    public void solveSudoku(char[][] board) {
        List<Set<Character>> rowRes = new ArrayList<>();
        List<Set<Character>> colRes = new ArrayList<>();
        List<Set<Character>> boxRes = new ArrayList<>();
        Set<Character> list = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            list.add((char) ('0' + i));
        }
        for (int i = 0; i < 9; i++) {
            rowRes.add(new HashSet<>(list));
            colRes.add(new HashSet<>(list));
            boxRes.add(new HashSet<>(list));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    rowRes.get(i).remove(board[i][j]);
                    colRes.get(j).remove(board[i][j]);
                    boxRes.get(i / 3 * 3 + j / 3).remove(board[i][j]);
                }
            }
        }
        sodukuDfs(board, rowRes, colRes, boxRes);

    }

    public static boolean sodukuDfs(char[][] board, List<Set<Character>> rowRes, List<Set<Character>> colRes, List<Set<Character>> boxRes) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // try every number
                    for (char c = '1'; c <= '9'; c++) {
                        // verify validation
                        if (!rowRes.get(i).contains(c) || colRes.get(j).contains(c) || boxRes.get(i / 3 * 3 + j / 3).contains(c))
                            continue;

                        board[i][j] = c;
                        rowRes.get(i).remove(c);
                        colRes.get(j).remove(c);
                        boxRes.get(i / 3 * 3 + j / 3).remove(c);

                        if (sodukuDfs(board, rowRes, colRes, boxRes))
                            return true;

                        board[i][j] = '.';
                        rowRes.get(i).add(c);
                        colRes.get(j).add(c);
                        boxRes.get(i / 3 * 3 + j / 3).add(c);
                    }
                    // tried every c, if no number can be placed here, return false
                    return false;

                }

            }
        }
        return true;
    }

    /**
     * 38. Count and Say - Medium
     *
     * @param n
     * @return
     * @Date 01/27/2026
     */
    public String countAndSay(int n) {
//        String res = "";
        // Better to use StringBuilder since using String to concat will generate a new String, which is really slow
        StringBuilder res = new StringBuilder();

        if (n == 1) {
            return "1";
        } else {
            String s = countAndSay(n - 1);

            int sum = 1;
            for (int i = 0; i < s.length(); i++) {
                char curNum = s.charAt(i);
                if (i < s.length() - 1 && curNum == s.charAt(i + 1)) {
                    sum++;
                } else {
                    String c = Integer.toString(sum);
//                    res = res + c + curNum;
                    res.append(c).append(curNum);
                    sum = 1;
                }
            }
        }
        return res.toString();
    }

    /**
     * 40. Combination Sum II - Medium
     *
     * @param candidates
     * @param target
     * @return
     * @Date 01/27/2026
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationNums2(candidates, target, 0, res, new ArrayList<>());
        return res;
    }

    private void combinationNums2(int[] candidates, int target, int start, List<List<Integer>> res, ArrayList<Integer> list) {
        if (target == 0) {
                res.add(new ArrayList<>(list));
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            combinationNums2(candidates, target - candidates[i], i + 1, res, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 1, 4};
        System.out.println(climbStairs(44));
    }
}
