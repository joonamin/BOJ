
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[][] board;
    static long B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        board = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] result = new long[N][N];
        long[][] a = new long[N][N];
        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
            for (int j = 0; j < N; j++) {
                a[i][j] = board[i][j];
            }
        }

        while (B > 0) {
            if (B % 2 == 1) {
                result = prod(result, a);
            }
            a = prod(a, a);
            B /= 2;
        }

        print(result);
    }

    private static void print(long[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static long[][] prod(long[][] original, long[][] operands) {
        long[][] result = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                for (int j = 0; j < N; j++) {
                    result[i][k] += original[i][j] * operands[j][k];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] %= 1000;
            }
        }
        return result;
    }
}