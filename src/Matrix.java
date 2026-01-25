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
     * 59. Spiral Matrix II - Medium
     * @Date - 01/25/2026
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int top = 0,bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1;
        int res[][] = new int[n][n];
        while (left < right && top < bottom) {
            // left to right >>>>>
            for (int i = left; i <= right; i++){
                res[top][i] = num++;
            }
            top++;
            // deal with right column
            // up to down
            for (int i = top; i <=bottom; i++) {
                res[i][right] = num++;
            }
            right--;
            // right to left  <<<<
            for (int i = right; i >=left ; i--) {
                res[bottom][i] = num++;
            }
            bottom--;
            // down to up
            for (int i = bottom; i >=top ; i--) {
                System.out.println("i = " + i+ ",num = "+num);
                res[i][left] = num++;
            }
            left++;
        }
        return res;
    }

    public int[][] generateMatrixLoop(int n) {
        // initially, stop y (row), move x (column)
        int x = 0, y = 0, dx = 1, dy = 0;
        int[][] res = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            // i is the number
            // x is column index, y is row index
            res[y][x] = i + 1;

            // the requirement of filling the array: within the range [0,n), and has not been filled (0)
            if (!(x + dx >=0 && x + dx < n && 0 <= y + dy && y + dy < n && res[y+dy][x+dx] == 0)) {
                // change the direction
                // swap the stop direction and change the direction
                int temp = dx;
                dx = -dy;
                dy = temp;
            }

            x += dx;
            y += dy;
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
     *
     * @param matrix
     * @Date 01/10/2026
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
            if (row[i] == 1) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (col[i] == 1) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    public void setZeroesO1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRow = false, firstCol = false;

        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }

        }

        //check if first row has zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // use the first row and column as tag
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // scan rows ->>>>>> using col number
        // start from 1 since need to protect the data in the first line
        for (int i = 1; i < m; i++) {
            // clear rows
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        // scan columns |
        for (int i = 1; i < n; i++) {
            // clear columns
            if (matrix[0][i] == 0) {
                for (int j = 1; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if (firstRow== true){
            for (int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }
        if (firstCol== true){
            for (int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
    }



}
