/**
 * @author louishu
 * @date 1/9/26 20:39
 * @description
 */
public class BinarySearch {

    /**
     * 35. Search Insert Position - Easy
     * @Date 01/09/2026
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

    /**
     * 74. Search a 2D Matrix - Medium
     * @Date 01/09/2026
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0, end = matrix.length - 1, targetRow = 0;

        for (int i = matrix.length - 1; i >0 ; i--) {
            if (matrix[i][0]< target){
                targetRow = i;
                break;
            }else if (matrix[i][0] == target){
                return true;
            }
        }

        for (int i = 0; i < matrix[targetRow].length; i++) {
            if (matrix[targetRow][i] == target) {
                return true;
            }
        }
        return false;
    }
}


