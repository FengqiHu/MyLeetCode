/**
 * @author louishu
 * @date 1/9/26 20:39
 * @description
 */
public class BinarySearch {

    /**
     * 33. Search in Rotated Sorted Array - Medium
     *
     * @param nums
     * @param target
     * @return
     * @Date 01/11/2026
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
//            } else if (target < nums[mid]) {
                // search right side
//                for (int i = mid + 1; i < right; i++) {
//                    if (nums[i] == target) {
//                        return i;
//                    }
//                }
//                right = mid - 1;
            } else if (nums[left] <= nums[mid]) {
                if (target <= nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // search left side
//                for (int i = left; i < mid - 1; i++) {
//                    if (nums[i] == target) {
//                        return i;
//                    }
//                }
//                left = mid + 1;
                if (target >= nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int searchBinary(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // find the start point (left margin)
        while (left < right) {
            // find the left, make the mid-point slide to right
            int mid = left+(right-left)/2+1;
            if (nums[0] <= nums[mid])
                left = mid;
            else
                right = mid - 1;
        }
        // finally, left == right
        int pivot = left;
        if (target >= nums[0]) {
            left = 0;
            right = pivot;
        } else {
            left = pivot;
            right = nums.length - 1;
        }
        while (left < right) {
            // find the left, make the mid-point slide to right
            int mid = left+(right-left)/2+1;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] <= target)
                left = mid;
            else
                right = mid-1;
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * 35. Search Insert Position - Easy
     *
     * @param nums
     * @param target
     * @return
     * @Date 01/09/2026
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
     *
     * @param matrix
     * @param target
     * @return
     * @Date 01/09/2026
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0, end = matrix.length - 1, targetRow = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[mid][0] <= target) {
                // find the first row that the first number greater than the target
                targetRow = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

//        for (int i = matrix.length - 1; i >0 ; i--) {
//            if (matrix[i][0]< target){
//                targetRow = i;
//                break;
//            }else if (matrix[i][0] == target){
//                return true;
//            }
//        }

        for (int i = 0; i < matrix[targetRow].length; i++) {
            if (matrix[targetRow][i] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * 162. Find Peak Element - Medium
     * @Date 01/09/2026
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                // if already greater than the right, then must has lower value on the left
                // searching lower value
                right = mid;
            } else {
                //
                left = mid + 1;
            }
        }

        return left;
    }

}


