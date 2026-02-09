import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author louishu
 * @date 1/24/26 00:41
 * @description
 */
public class StackSet {
    /**
     * 32. Longest Valid Parentheses - hard
     *
     * @param s
     * @return
     * @Date - 01/24/2026
     */
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        // start represents the current valid start position
        // () => i = 1-start = 2
        int start = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // record the start position
                stack.push(i);
            } else {
                // the split pos, mark as start
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    // completely valid - ()()()
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - start);
                    } else {
                        // possibly has other - (()())
                        // keep the current valid start
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        return res;
    }

    // Q84 单调栈 - Monotonous stack
    public static int largestRectangleAreaStack(int[] heights) {
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
            // width = (leftMin[i]- rightMin[i])
            res = Math.max(res, heights[i] * (rightMin[i] - leftMin[i] - 1));
        }
        return res;
    }

    /**
     * 739. Daily Temperatures - medium
     *
     * @param temp
     * @return
     * @Date - 02/09/2026
     */
    public int[] dailyTemperatures(int[] temp) {
        // find the first one that bigger than current
        int n = temp.length;
        int res[] = new int[n];
        // Deque is must faster than Stack
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // Monotonous stack
            // if stack is empty or the top element is smaller than current
            while (!stack.isEmpty() && temp[stack.peek()] < temp[i]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
             // finally push the current element into stack
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()())"));
    }

}
