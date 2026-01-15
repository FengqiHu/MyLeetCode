import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author louishu
 * @date 1/15/26 14:13
 * @description
 */
public class GreedyAlgo {

    /**
     * Phone Call Problem
     * Giving three arrays, find the optimal subset of non-overlapping phone calls to maximize a total volume.
     * Example:
     * start    = {10, 5, 15, 18, 30};
     * duration = {30, 12, 20, 35, 35};
     * volume   = {50, 51, 20, 25, 10};
     *
     * Output: 76 (Call 2 + Call 4 = 51 + 25)
     *
     * This is local optimal solution
     */
    static class Order {
        int start;
        long end;
        int volume;

        public Order(int start, long end, int volume) {
            this.start = start;
            this.end = end;
            this.volume = volume;
        }
    }

    public static int phoneCalls(int[] start, int[] duration, int[] volume) {

        List<Order> orders = new ArrayList<>();
        int n = start.length;
        if (n == 0) return 0;

        for (int i = 0; i < n; i++) {
            orders.add(new Order(start[i], start[i] + duration[i], volume[i]));
        }

        // sort by the end date
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order a, Order b) {
                return Long.compare(a.end, b.end);
            }
        });

        long[] dp = new long[n];
        dp[0] = orders.get(0).volume;

        for (int i = 1; i < n; i++) {
            // store the previous possible best solution
            long untake = dp[i - 1];
            long take = orders.get(i).volume;

            // choose the closest order, minimum the time margin between orders
            int closest = searchClosest(orders, i);

            if (closest != -1) {
                take += dp[closest];
            }
            dp[i] = Math.max(untake, take);
        }

        return (int) dp[n - 1];
    }
    public static int searchClosest(List<Order> orders, int index) {
        int left = 0, right = index - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (orders.get(mid).end <= orders.get(index).start) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] start = {10,10,50,60,40};
        int[] duration = {20,40,50,30,100};
        int[] volume = {10,20,30,40,50};

        System.out.println(backpackProblem(100, duration, volume));
    }
}
