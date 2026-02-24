
import java.io.*;
import java.util.*;

public class Main {

    static int N, B, K;
    static int[][] board;
    static int[][][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1][251];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = deduct(acc(map[i][j - 1], map[i - 1][j]), map[i - 1][j - 1]);
                map[i][j][board[i][j]] += 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 1; k <= K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            sb.append(solve(r, c)).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] acc(int[] a, int[] b) {
        int[] result = new int[251];
        for (int i = 0; i < 251; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

    private static int[] deduct(int[] a, int[] b) {
        int[] result = new int[251];
        for (int i = 0; i < 251; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }

    private static int solve(int r, int c) {
        int r2 = r + B - 1;
        int c2 = c + B - 1;

        int min = Integer.MAX_VALUE;
        int max = -1;

        for (int i = 0; i <= 250; i++) {
            int count = map[r2][c2][i] - map[r - 1][c2][i] - map[r2][c - 1][i] + map[r - 1][c - 1][i];

            if (count > 0) {
                max = Math.max(max, i);
                min = Math.min(min, i);
            }
        }

        return max - min;
    }
}