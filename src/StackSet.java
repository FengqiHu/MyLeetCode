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

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()())"));
    }

}
