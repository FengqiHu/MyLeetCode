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


    public static void main(String[] args) {
        int height[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(generateParenthesis(3));
    }

}
