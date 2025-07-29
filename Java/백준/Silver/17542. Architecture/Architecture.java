
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int R = readInt(), C = readInt();
        int[] rows = new int[R];
        int[] cols = new int[C];
        for (int i = 0; i < R; i++) {
            rows[i] = readInt();
        }
        for (int i = 0; i < C; i++) {
            cols[i] = readInt();
        }

        int[][] board = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] = Math.min(rows[i], cols[j]);
            }
        }

        boolean flag = true;
        for (int i = 0; flag && i < R; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; flag && j < C; j++) {
                max = Math.max(max, board[i][j]);
            }
            if (max != rows[i])
                flag = false;
        }
        for (int j = 0; flag && j < C; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; flag && i < R; i++) {
                max = Math.max(max, board[i][j]);
            }
            if (max != cols[j])
                flag = false;
        }
        if (flag) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
    }

    private static int readInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

}