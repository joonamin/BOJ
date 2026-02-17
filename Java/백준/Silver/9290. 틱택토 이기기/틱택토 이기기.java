
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            char[][] board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 0; j < 3; j++) {
                    board[i][j] = line[j];
                }
            }
            char next = br.readLine().charAt(0);
            solve(board, next);
            sb.append(String.format("Case %d:\n", tc));
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void solve(char[][] board, char next) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = next;
                    if (checkNextWin(board, next)) {
                        return;
                    }
                    board[i][j] = '-';
                }
            }
        }
    }

    private static boolean checkNextWin(char[][] board, char next) {
        for (int i = 0; i < 3; i++) {
            boolean fRows = true, fCols = true;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != next) {
                    fRows = false;
                }
                if (board[j][i] != next) {
                    fCols = false;
                }
            }
            if (fRows || fCols)
                return true;
        }

        int i = 0, j = 0;
        boolean cross = true;
        while (i < 3 && j < 3) {
            if (board[i][j] != next) {
                cross = false;
                break;
            }
            i += 1;
            j += 1;
        }
        if (cross)
            return true;
        i = 0;
        j = 2;
        cross = true;
        while (i < 3 && j >= 0) {
            if (board[i][j] != next) {
                cross = false;
                break;
            }
            i += 1;
            j -= 1;
        }
        return cross;
    }
}