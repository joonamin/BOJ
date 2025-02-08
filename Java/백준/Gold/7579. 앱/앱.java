import java.util.*;
import java.io.*;

public class Main {
    static int N, M, m[], c[];
    static int[][] dp;

    private static int solve() {
        dp = new int[N][10001];
        for (int i = c[0]; i <= 10000; i++) {
            dp[0][i] = m[0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= 10000; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j < c[i])
                    continue;
                if (j >= 1)
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - c[i]] + m[i]);
            }
        }

        int result = 0;
        for (int i = 0; i <= 10000; i++) {
            if (dp[N - 1][i] >= M) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        m = new int[N];
        c = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}