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
     * 89. Gray Code - Medium
     *
     * @param n
     * @return
     * @Date - 02/01/2026
     */
    public static List<Integer> grayCode(int n) {
        /**
         * n = 2
         * 00
         * 10
         * ---
         * 11
         * 01
         * 除最后一位外，每一位的数字，都是从0开始，并且上下对称，所以n基于n-1的结果，左移一位，变成2进制后，再复制一遍+1
         */
        List<Integer> ans = new ArrayList<>();
        ans.add(0);

        while (n > 0) {
            for (int i = ans.size() - 1; i >= 0; i--) {
                int val = ans.get(i);
                // move one digit left
                val <<= 1;
                //update value
                ans.set(i, val);
                System.out.println(val);
                ans.add(val + 1);
            }
            n--;
        }

        return ans;
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
                // choosing the biggest one between neighbors
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        for (int i = 0; i < ratings.length; i++) {
            sum += candies[i];
        }
        return sum;
    }

    /**
     * 134. Gas Station - Medium
     * 01/06/2026
     *
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
//        int sum = 0;
//        for (int i = 0; i < n; i++) {
//            sum += gas[i] - cost[i];
//        }
//        if (sum<0){
//            return -1;
//        }
//        for (int i = 0; i < n; i++) {
//            int time = 0, pos = i, fuel = gas[i];
//            while (time != n) {
//                // prevent overflow
//                if (pos == n) {
//                    pos = 0;
//                }
//                if (fuel == 0){
//                    break;
//                }
//                if (fuel - cost[pos] < 0) {
//                    break;
//                } else if (cost[pos] == gas[(pos+1)%n]) {
//                    pos++;
//                }else {
//                    fuel = fuel - cost[pos] + gas[(pos + 1) % n];
//                    pos++;
//                }
//                time++;
//            }
//            if (time == n) {
//                return i;
//            }
//        }
        int sumtank = 0, curtank = 0, startpos = 0;
        for (int i = 0; i < n; i++) {
            curtank += gas[i] - cost[i];
            sumtank += gas[i] - cost[i];
            if (curtank < 0) {
                curtank = 0;
                startpos = i + 1;
            }
        }
        if (sumtank >= 0) {
            return startpos;
        }
        return -1;
    }

    /**
     * 274. H-Index - Medium
     *
     * @param citations
     * @return
     * @Date 01/11/2026
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int h = 0;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length / 2; i++) {
            int temp = citations[i];
            citations[i] = citations[n - 1 - i];
            citations[n - 1 - i] = temp;
        }

        for (int i = 0; i < n; i++) {
            h = Math.max(h, Math.min(citations[i], i + 1));
        }
        return h;
    }


    /**
     * 151. Reverse Words in a String - Medium
     *
     * @param s
     * @return
     * @Date 01/11/2026
     */
    public String reverseWords(String s) {
        int start = 0;
        String res = "";
        List<String> str = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            while (i < s.length() && s.charAt(i) != ' ') {
                i++;
            }
            str.add(s.substring(start, i));
            while (i < s.length() - 1 && s.charAt(i) == ' ') {
                i++;
            }
            start = i;
        }
        System.out.println();
        // add the last word
        str.add(s.substring(start, s.length()));
        for (int i = str.size() - 1; i >= 0; i--) {
            res = res + str.get(i) + " ";
        }
        return res.trim();
    }

    /**
     * 42. Trapping Rain Water - Hard
     *
     * @param height
     * @return
     * @Date - 02/07/2026
     */

    public int trap(int[] height) {
        int n = height.length;
        int leftMax[] = new int[n];
        int rightMax[] = new int[n];
        int sum = 0;
        leftMax[0] = height[0];
        rightMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // the water can be stored between leftMax and rightMax
        for (int i = 0; i < n; i++) {
            int h = Math.min(leftMax[i], rightMax[i]);
            if (h > height[i]) {
                sum += h - height[i];
            }
        }
        return sum;
    }

    /**
     * 84. Largest Rectangle in Histogram - Hard
     * @Date - 02/08/2026
     *
     * @param heights
     * @return
     * @Date - 02/07/2026
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int leftMin[] = new int[n];
        int rightMin[] = new int[n];
        leftMin[0] = -1;
        rightMin[n - 1] = n;
        for (int i = 1; i < n; i++) {
            int t = i - 1;
            while (t >= 0 && heights[t] >= heights[i]) {
                // jump to the previous smaller element
                t = leftMin[t];
            }
            leftMin[i] = t;
        }

        rightMin[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            int t = i + 1;
            while (t < n && heights[t] >= heights[i]) {
                t = rightMin[t];
            }
            rightMin[i] = t;
        }
        int res = 0;
        // the water can be stored between leftMax and rightMax
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (rightMin[i]- leftMin[i]-1));
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "sg";
        int i[] = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(i));
    }

}
