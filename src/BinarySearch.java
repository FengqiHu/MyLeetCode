/**
 * @author louishu
 * @date 1/9/26 20:39
 * @description
 */
public class BinarySearch {

    /**
     * 35. Search Insert Position - Easy
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        i = (i + j) / 2;
        if (nums[i] < target) {
            return i + 1;
        } else {
            return i;

        }
    }
}
