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
     * 01/03/2026
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
     * 01/03/2026
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
     * 01/03/2026
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    right--;
                    while (nums[left] == nums[left + 1] && left < right) {
                        left++;
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 16. 3Sum Closest - Medium
     * 01/03/2026
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int mindiff = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = sum - target;
                if (diff < 0) {
                    left++;
                } else if (diff > 0) {
                    right--;
                } else {
                    return sum;
                }
                if (Math.abs(diff) < Math.abs(mindiff)) {
                    mindiff = diff;
                    result = sum;
                }
            }
        }
        return result;
    }


    /**
     * 17. Letter Combinations of a Phone Number - Medium
     * 01/03/2026
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        String str[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();

        // loop the digits, up to 4 times
        for (int i = 0; i < digits.length(); i++) {
            int num = Integer.parseInt(String.valueOf(digits.charAt(i)));
            int times = result.size();

            // loop the digits number of the string
            // first time, directly insert number
            if (result.isEmpty()) {
                for (int k = 0; k < str[num - 2].length(); k++) {
                    result.add(String.valueOf(str[num - 2].charAt(k)));
                }
                continue;
            }
            for (int j = 0; j < times; j++) {
                for (int k = 0; k < str[num - 2].length(); k++) {
                    result.add(result.get(j) + str[num - 2].charAt(k));
                }
            }
            for (int l = 0; l < times; l++) {
                result.remove(0);
            }

        }
        return result;
    }

    /**
     * 27. Remove Duplicates - Easy
     * 01/04/2026
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[sum] = nums[i];
                sum++;
            }
        }

        return sum;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                // slow records the unique number index
                // fast detects future numbers
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        int[] array = {1,1,2,2,3,4,4};
        System.out.println(removeDuplicates(array));
    }
}
