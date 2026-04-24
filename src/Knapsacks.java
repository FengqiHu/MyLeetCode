import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author louishu
 * @date 4/24/26 15:26
 * @description
 */
public class Knapsacks {


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
     * 1049. last Stone Weight II
     * imagine that split the sum into two parts, and try to fill one side
     * similar with 416
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }

        int target = sum / 2;

        int dp[] = new int[target + 1];

        for (int store : stones) {
            for (int i = target; i >= store; i--) {
                dp[i] = Math.max(dp[i], dp[i - store] + store);
            }
        }
        return Math.abs(sum - 2 * dp[target]);

    }

    /**
     * 474. Ones and Zeroes
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        List<Number> list = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            Number num = new Number();
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    num.setZero(num.getZero() + 1);
                } else {
                    num.setOne(num.getOne() + 1);
                }
            }
            list.add(num);
        }

        int dp[][] = new int[m + 1][n + 1];

        for (Number num : list) {
            for (int i = m; i >= num.getZero(); i--) {
                for (int j = n; j >= num.getOne(); j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - num.getZero()][j - num.getOne()] + 1);
                }
            }
        }
        return dp[m][n];
    }

    class Number {
        int one = 0;
        int zero = 0;

        public int getOne() {
            return one;
        }

        public void setOne(int one) {
            this.one = one;
        }

        public int getZero() {
            return zero;
        }

        public void setZero(int zero) {
            this.zero = zero;
        }
    }

    /**
     * 518. Coin Change II
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;

        // has dp[i] solutions when amount = i
        int dp[] = new int[amount + 1];
        // has one solution if amount = 0 (not choose)
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 494. Target Sum
     * <p>
     * split the array into two subsets, one is +, one is -
     * finally calculated the target volume  = (sum + target) / 2
     * then equals Knapsack problem - try to use the nums in the array to fill the Knapsack
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if ((sum + target) % 2 == 1 || (sum + target) < 0) {
            return 0;
        }

        int volume = (sum + target) / 2;
        int dp[] = new int[volume + 1];

        // the first one should be initialized as 1
        // when nums = [0] and target = 0, the total solution is 1
        dp[0] = 1;

        for (int num : nums) {
            for (int j = volume; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[volume];
    }

    /**
     * 377. Combination Sum IV
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        // has dp[i] solutions when amount = i
        int dp[] = new int[target + 1];
        // has one solution if amount = 0 (not choose)
        dp[0] = 1;

        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /**
     * 279. Perfect Squares
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        double sqrt = Math.sqrt(n);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= sqrt; i++) {
            list.add(i * i);
        }
        int dp[] = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }
        for (int num : list) {
            for (int i = num; i <= n; i++) {
                if (i >= num) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - num]);
                }
            }
        }
        return dp[n];
    }

}
