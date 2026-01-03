import java.util.HashMap;
import java.util.Map;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static long numGoodSubarrays(int[] nums, int k) {
        int count = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= k) {
                end = i - 1;
                break;
            }
        }
        System.out.println(end);
        for (int i = nums.length - 1; i >= end; i--) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum % k == 0) {
                    count++;
                }
            }
        }
        return count;

    }

//    public static long countStableSubarrays(int[] capacity) {
//        int count = 0;
//        long[] presum = new long[capacity.length+1];
//        presum[0]=0;
//
//        for (int i = 1; i < capacity.length; i++){
//            presum[i] = capacity[i-1] + presum[i-1];
//        }
//
//
//        for (int i = 0; i < capacity.length; i++) {
//            for (int j = capacity.length - 1; j >= i + 2; j--) {
//                if (capacity[i] == capacity[j]) {
//                    long sum = presum[j]- presum[i+1];
//                    if (sum == capacity[i]) {
//                        count++;
//                    }
//                }
//            }
//        }
//        return count;
//    }
    public static long countStableSubarrays(int[] capacity) {
        int n = capacity.length;
        long count = 0;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + capacity[i];
        }

        // key: capacity value
        // value: Map<prefix value, frequency>
        Map<Integer, Map<Long, Integer>> map = new HashMap<>();

        // 从左到右遍历
        for (int j = 0; j < n; j++) {
            // 对于 j，我们想找所有 i < j-1
            // 满足 prefix[j] == prefix[i+1] + capacity[i] 且 capacity[j]==capacity[i]
            Map<Long, Integer> sub = map.get(capacity[j]);
            if (sub != null && sub.containsKey(prefix[j])) {
                count += sub.get(prefix[j]);
            }

            // 将 i 位置的信息加入表中，为后续 j 使用
            // （只有在 j >= i+2 时才能形成有效区间）
            long key = prefix[j + 1] + capacity[j];
            map.computeIfAbsent(capacity[j], k -> new HashMap<>())
                    .merge(key, 1, Integer::sum);
        }

        return count;
    }


    public static void main(String[] args) {
        System.out.println(countStableSubarrays(new int[]{536870912,536870912,536870912,536870912,536870912,536870912,536870912,536870912,536870912,536870912,536870912,536870912}));
    }
}