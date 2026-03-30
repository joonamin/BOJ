
import java.io.*;
import java.util.*;

public class Main {

    static int N, K, board[][];
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.readLine();
        if (K == 0) {
            int res = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 2; j++) {
                    res += board[i][j];
                }
            }
            System.out.println(res);
            return;
        }

        dp = new int[N][3][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(dp[i][j], -0x3f3f3f3f);
            }
        }
        dp[0][0][0] = board[0][0] + board[0][1];
        dp[0][1][1] = board[0][0];
        dp[0][2][1] = board[0][1];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][0][j] = max(dp[i - 1][0][j], dp[i - 1][1][j], dp[i - 1][2][j]) + board[i][0] + board[i][1];
                if (j == 0)
                    continue;
                int t1 = max(dp[i - 1][0][j - 1], dp[i - 1][1][j - 1]);
                int t2 = max(dp[i - 1][0][j - 1], dp[i - 1][2][j - 1]);
                dp[i][1][j] = t1 + board[i][0];
                dp[i][2][j] = t2 + board[i][1];
            }
        }
        System.out.println(max(dp[N - 1][0][K], dp[N - 1][1][K], dp[N - 1][2][K]));
    }

    private static int max(int a, int b, int c) {
        return Math.max(max(a, b), c);
    }

    private static int max(int a, int b) {
        return Math.max(a, b);
    }
}