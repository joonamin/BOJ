import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int[][] arrays;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        int[] params = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = params[0];
        M = params[1];
        H = params[2];
        arrays = new int[N][];
        for (int i = 0; i < N; i++) {
            params = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arrays[i] = params;
        }
        System.out.println(solve());
    }

    private static int solve() {
        int[][] dp = new int[N][H + 1];
        dp[0][0] = 1;
        for (int i = 0; i < arrays[0].length; i++) {
            dp[0][arrays[0][i]] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int h = 0; h <= H; h++) {
                dp[i][h] = dp[i - 1][h];
                for (int j = 0; j < arrays[i].length; j++) {
                    if (h >= arrays[i][j])
                        dp[i][h] = ((dp[i][h] % MOD) + (dp[i - 1][h - arrays[i][j]] % MOD)) % MOD;
                }
            }
        }
        return dp[N - 1][H];
    }
}