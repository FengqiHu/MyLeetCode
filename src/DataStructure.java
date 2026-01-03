import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DataStructure {
    class MyStack {
        public static void initStack() {
            Stack<Integer> stack = new Stack<>();
            stack.push(1);
            stack.push(2);
            stack.push(3);
            System.out.println(stack);
            System.out.println("peek: " + stack.peek());
            System.out.println(stack);
            System.out.println("remove: " + stack.remove(1));
            System.out.println(stack);
            System.out.println("pop: " + stack.pop());
            System.out.println(stack);
        }
    }

    class MyQueue {
        public static void initQueue() {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(1);
            queue.add(2);
            queue.add(3);
            System.out.println(queue);
            System.out.println("peek: " + queue.peek());
            System.out.println(queue);
            System.out.println("poll: " + queue.poll());
            System.out.println(queue);

        }
    }

    /**
     * 20. Valid Parentheses
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> input = new Stack<>();
        Stack<Character> output = new Stack<>();
        if (s.length() % 2 == 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            // left, push to stack
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                input.push(s.charAt(i));
            } else {
                if (input.size()!=0 && validate(input.peek(), s.charAt(i))) {
                    input.pop();
                } else {
                    return false;
                }
            }
        }
        if (input.size()==0)
            return true;
        else
            return false;
    }

    public static boolean validate(char a, char b) {
        if (a == '{' && b == '}') {
            return true;
        } else if (a == '[' && b == ']') {
            return true;
        } else if (a == '(' && b == ')') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DataStructure.MyStack.initStack();
        System.out.println("\\\\\\\\\\\\");
        DataStructure.MyQueue.initQueue();
        System.out.println();

    }
}
