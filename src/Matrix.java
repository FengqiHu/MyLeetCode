import java.util.ArrayList;
import java.util.List;

/**
 * @author louishu
 * @date 1/10/26 22:51
 * @description
 */
public class Matrix {
    /**
     * 54. Spiral Matrix - Medium
     *
     * @param matrix
     * @return
     * @Date 01/10/2026
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // row direction, true is right, false is left
        // col direction, true is down, false is up
        int left = 0, right = matrix[0].length - 1;
        int top = 0, bottom = matrix.length - 1;
        List<Integer> res = new ArrayList<>();
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            // if only left one row, no need to go right again
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;

            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;

            }

        }
        return res;
    }

    /**
     * 48. Rotate Image - Medium
     *
     * @param matrix
     * @Date 01/10/2026
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int tmp[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[n - j][i] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            matrix[i] = tmp[i];
        }
    }

    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        // 1. transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 2. reverse row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }

    /**
     * 73. Set Matrix Zeroes - Medium
     * @Date 01/10/2026
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int row[] = new int[m];
        int col[] = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (row[i] == 1){
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (col[i] == 1){
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

}
