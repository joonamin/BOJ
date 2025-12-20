
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], M = input[1];
        int[][] v = new int[N][M];
        for (int i = 0; i < N; i++) {
            v[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // dp[i][j] := i번째 stage까지 j무기로 접근했을 때 클리어할 수 있는 최소 시간
        // dp[i][j] = min_k(dp[i-1][k] + v[i][j]) [단, k != j]
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < M; i++) {
            dp[0][i] = v[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    if (j == k)
                        continue;
                    if (dp[i][j] == -1 || dp[i][j] > dp[i - 1][k] + v[i][j]) {
                        dp[i][j] = dp[i - 1][k] + v[i][j];
                    }
                }
            }
        }
        System.out.println(Arrays.stream(dp[N - 1]).min().getAsInt());
    }

}