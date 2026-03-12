import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SortAlgorithm {
    public static int[] bubbleSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = i; j < num.length; j++) {
                if (num[i] > num[j]) {
                    int temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }
        return num;
    }

    /**
     * choose the minimum value and swap with the first one
     *
     * @param num
     * @return
     */
    public static int[] selectSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            // assume the minimum value is [i]
            int min = i;
            for (int j = i; j < num.length; j++) {
                if (num[min] > num[j]) {
                    min = j;
                }
            }
            // if no other value smaller than [i], skip
            if (i != min) {
                int temp = num[min];
                num[min] = num[i];
                num[i] = temp;
            }
        }
        return num;
    }

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

    public static int[] getSneakyNumbers(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            if (map.get(key) < 2) {
                iterator.remove();
            }
        }
        int[] res = new int[map.size()];
        int index = 0;
        for (Integer key : map.keySet()) {
            res[index] = key;
            index++;
        }
        return res;
    }

    public static int[] shellSort(int[] nums) {
        int n = nums.length;
        int h = n / 2;   // generally start at n/2

        while (h > 0) {
            // i is the second one in an array, i compare to the i-h = 0 from the beginning
            // when h=1, iut becomes normal insert sort
            for (int i = h; i < n; i++) {
                // keep the last one, cause the former will be pushed back, so we need to store the last one and finally give it to the first
                int temp = nums[i];
                int j = i;

                // former > later, give the former to the later
                while (j >= h && nums[j - h] > temp) {
                    nums[j] = nums[j - h];
                    j -= h;
                }
                // give the current one to the first
                nums[j] = temp;
            }
            h /= 2;
        }
        return nums;
    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start > end)
            return;
        // in this case, we always choose the left one as base element
        int pivot = nums[start];
        int left = start, right = end;

        while (left < right) {
            // find the first smaller one that should be moved to the left
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            // find the first larger one that should be moved to the right
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            // switch them,
            if (left < right) {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
            }
        }
        // at the end, left==right. this position is the pivot should be
        // also, nums[left] must be the one that smaller than pivot
        // so move it to the left
        nums[start] = nums[left];
        nums[left] = pivot;

        quickSort(nums, start, right - 1);
        quickSort(nums, left + 1, end);

    }


    public static void main(String[] args) {
        int[] num = {5, 4, 6, 3, 2, 1, 8, 7};
//        int[] num = {5, 4, 3, 2, 1};
        quickSort(num,0,num.length-1);
        System.out.println(Arrays.toString(num));
    }
}
