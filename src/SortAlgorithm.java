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
            }else{
                map.put(nums[i],1);
            }
        }
        Iterator<Integer> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            Integer key = iterator.next();
            if (map.get(key) < 2){
                iterator.remove();
            }
        }
        int [] res = new int[map.size()];
        int index = 0;
        for (Integer key: map.keySet()){
            res[index] = key;
            index++;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] num = {5, 4, 6, 3, 2, 1, 8, 7};
        int[] result = insertSort(num);
        System.out.println(Arrays.toString(result));
        int[] nums = {7,1,5,4,3,4,6,0,9,5,8,2};
        System.out.println(Arrays.toString(getSneakyNumbers(nums)));
    }
}
