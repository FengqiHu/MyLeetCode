import java.util.HashMap;

public class Two_Sum {
    public int[] twoSumBasic(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int dif = target - nums[i];
            if (map.containsKey(dif) && map.get(dif) != i) {
                return new int[]{i, map.get(dif)};
            }
        }
        return null;
    }

    /**
     * 7. Reverse Integer
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int result = 0;
        while (x != 0){
            int temp = x % 10;
            if(result > Integer.MAX_VALUE/10 || result< Integer.MIN_VALUE/10){
                return 0;
            }
            result = result * 10 + temp;
            x /=10;
            Character s = '2' ;
            Integer.parseInt(s.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        int nums[] = {3, 2, 4};
        int target = 6;
        int[] result = new Two_Sum().twoSum(nums, target);
        System.out.println("[" + result[0] + "," + result[1] + "]");
        System.out.println(reverse(1539964699));
    }
}
