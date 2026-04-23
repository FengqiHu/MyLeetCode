import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author louishu
 * @date 4/22/26 14:17
 * @description
 */
public class Test {

    static class Player {
        int row;
        int col;
//        List<Coordinates> body = new ArrayList<>();

        public Player(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("input the N");
        boolean flag = true;
        int n = sc.nextInt();
        char board[][] = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        Player red = new Player(1, 1);
        Player blue = new Player(2, 2);

        // true for RED and false for BLUE
        printBoard(board, red, blue);
        String direction = sc.nextLine();

        while (true) {
            direction = sc.nextLine();
            System.out.println("dir=" + direction);
            Player curPlayer = red;
            if (!flag)
                curPlayer = blue;
            if (isValid(direction, n, curPlayer, red, flag)) {
                flag = !flag;
                printBoard(board, red, blue);

                continue;
            } else {
                System.out.println("invalid input!");
            }
            System.out.println("current board");

        }
    }


    public static boolean isValid(String dirction, int n, Player player, Player player2, boolean flag) {
        if (dirction.equals("left")) {
            if (player.col <= 0 || (player.getCol() - 1==player2.col && player.getRow()==player2.getRow())) {
                return false;
            } else {
                player.setCol(player.getCol() - 1);
                return true;
            }
        }
        if (dirction.equals("right")) {
            if (player.col >= n - 1) {
                return false;
            } else {
                player.setCol(player.getCol() + 1);
                return true;
            }
        }
        if (dirction.equals("up")) {
            if (player.row <= 0) {
                return false;
            } else {
                player.setRow(player.getRow() - 1);
                return true;
            }
        }
        if (dirction.equals("down")) {
            if (player.row >= n - 1) {
                return false;
            } else {
                player.setRow(player.getRow() + 1);
                return true;
            }
        }
        return false;
    }


    public static void printBoard(char[][] board, Player red, Player blue) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == red.row && j == red.col) {
                    System.out.print("R");
                } else if (i == blue.row && j == blue.col) {
                    System.out.print("B");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.println();
        }

    }
}
