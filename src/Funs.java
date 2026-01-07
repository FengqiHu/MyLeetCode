import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author louishu
 * @date 1/3/26 16:28
 * @description
 */
public class Funs {

    /**
     * 11. Container with most water
     * 01/03/2026
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int maxCap = 0;
//        for (int i = 0; i < height.length; i++) {
//            for (int j = i+1; j < height.length; j++) {
//                int cap = ((height[i]>height[j])? height[j]: height[i]) * (j-i);
//                if (cap>maxCap){
//                    maxCap = cap;
//                }
//
//            }
//        }
        int left = 0, right = height.length - 1;
        while (left < right) {
            int cap = ((height[left] > height[right]) ? height[right] : height[left]) * (right - left);
            if (cap > maxCap) {
                maxCap = cap;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxCap;
    }

    /**
     * 22. Generate Parentheses - Medium
     * 01/04/2026
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<List<Integer>> possible = new ArrayList<>();
        List<String> res = new ArrayList<>();
        String str = "";
        // define initial str
        for (int i = 0; i < n; i++) {
            str = str + "(";
        }
        int[] path = new int[n];

        dfs(n, 1, 0, n, new ArrayList<>(), possible);

        for (List<Integer> list : possible) {
            String tmp = "";
            for (int i : list) {
                tmp = tmp + "(";
                for (int j = 0; j < i; j++) {
                    tmp = tmp + ")";
                }
            }
            res.add(tmp);
        }
        return res;

    }

    /**
     * @param n
     * @param pos    current processing position
     * @param sum    the sum of the first pos-1 bits
     * @param remain the remain number
     * @param path   current constructing array
     * @param res
     */

    private static void dfs(int n, int pos, int sum, int remain, List<Integer> path, List<List<Integer>> res) {

        if (sum > pos - 1) return;

        // 所有位置填完
        if (pos > n) {
            // check if using out the number and the last digit > 0
            if (remain == 0 && path.get(n - 1) > 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        // run out of number
        if (remain < 0) return;

        // the current value must less than pos
        int maxVal = Math.min(pos, remain);

        // the last digit > 0
        int start = (pos == n) ? 1 : 0;

        // loop all the possible values
        for (int v = start; v <= maxVal; v++) {
            path.add(v);
            dfs(n, pos + 1, sum + v, remain - v, path, res);
            path.remove(path.size() - 1);
        }
    }


    /**
     * 135. Candy - Hard
     *
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];

        // init array, every child get at least one candy
        Arrays.fill(candies, 1);

//        Boolean flag = false;
//        int sum = ratings.length;
//        while (!flag) {
//            flag = true;
//            for (int i = 0; i < ratings.length; i++) {
//                //check left
//                if(i>0 && (ratings[i]>ratings[i-1]) && (candies[i]<=candies[i-1])){
//                    sum++;
//                    candies[i]++;
//                    flag = false;
//                }
//                if (i<ratings.length-1 &&(ratings[i]>ratings[i+1])&& (candies[i] <= candies[i+1])){
//                    sum++;
//                    candies[i]++;
//                    flag = false;
//                }
//            }
//
//        }
        int n = ratings.length;
        int sum = 0;
        // from left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // from right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        for (int i = 0; i < ratings.length; i++) {
            sum += candies[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int height[] = {1, 2, 87, 87, 87, 2, 1};
        System.out.println(candy(height));
    }

}
