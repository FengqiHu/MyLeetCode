/**
 * @author louishu
 * @date 1/9/26 15:22
 * @description
 */
public class DynamicPlan {

    /**
     * 198. House Robber - Medium
     * 01/09/2026
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        int memo[] = new int[n];
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int tmp = nums[i] + nums[i-2];
            memo[i] = Math.max(memo[i - 1], tmp);
        }
        return memo[n - 1];
    }

}
