import java.util.*;

public class IntegerSet {
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
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int temp = x % 10;
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            result = result * 10 + temp;
            x /= 10;
            Character s = '2';
            Integer.parseInt(s.toString());
        }
        return result;
    }

    /**
     * 12. Integer to Roman - Medium
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        String str = "";
        if (num / 1000 > 0) {
            int t = num / 1000;
            for (int i = 0; i < t; i++) {
                str = str + 'M';
            }
            num %= 1000;

        }
        if (num / 100 > 0) {
            int h = num / 100;
            if (h == 9) {
                str = str + "CM";
            } else if (h == 4) {
                str = str + "CD";
            } else if (h < 4) {
                for (int i = 0; i < h; i++) {
                    str = str + 'C';
                }
            } else {
                str = str + 'D';
                for (int i = 0; i < h - 5; i++) {
                    str = str + 'C';
                }
            }
            num %= 100;
        }
        if (num / 10 > 0) {
            int t = num / 10;
            if (t == 9) {
                str = str + "XC";
            } else if (t == 4) {
                str = str + "XL";
            } else if (t < 4) {
                for (int i = 0; i < t; i++) {
                    str = str + 'X';
                }
            } else {
                str = str + 'L';
                for (int i = 0; i < t - 5; i++) {
                    str = str + 'X';
                }
            }
            num %= 10;
        }
        if (num == 9) {
            str = str + "IX";
        } else if (num == 4) {
            str = str + "IV";
        } else if (num < 4) {
            for (int i = 0; i < num; i++) {
                str = str + 'I';
            }
        } else {
            str = str + 'V';
            for (int i = 0; i < num - 5; i++) {
                str = str + 'I';
            }
        }

        return str;
    }

    public static String intToRomanDemo(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }


    /**
     * 15. 3Sum - Medium
     *
     * @param nums
     * @return
     */
    public static int[] insertSort(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int j = i;
            while (j > 0 && num[j] < num[j - 1]) {
                int tmp = num[j];
                num[j] = num[j - 1];
                num[j - 1] = tmp;
                j--;
            }

        }
        return num;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        nums = insertSort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while(left<right) {
                if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    right--;
                    while(nums[left]==nums[left+1] && left<right){
                        left++;
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        int[] array = {0,0,0};
        System.out.println(threeSum(array));
    }
}
