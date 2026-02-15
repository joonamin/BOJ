
import java.io.*;
import java.util.*;

public class Main {
    static int N, K, a[][];
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[N][K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][K];
        int max1Idx = -1, max2Idx = -1;
        for (int j = 0; j < K; j++) {
            dp[0][j] = a[0][j];
            if (max1Idx == -1 || dp[0][max1Idx] < dp[0][j]) {
                max2Idx = max1Idx;
                max1Idx = j;
            } else if (max2Idx == -1 || dp[0][max2Idx] < dp[0][j]) {
                max2Idx = j;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < K; j++) {
                if (max1Idx != j) {
                    dp[i][j] = dp[i - 1][max1Idx] + a[i][j];
                } else {
                    dp[i][j] = dp[i - 1][max2Idx] + a[i][j];
                }
            }
            max1Idx = max2Idx = -1;
            for (int j = 0; j < K; j++) {
                if (max1Idx == -1 || dp[i][max1Idx] < dp[i][j]) {
                    max2Idx = max1Idx;
                    max1Idx = j;
                } else if (max2Idx == -1 || dp[i][max2Idx] < dp[i][j]) {
                    max2Idx = j;
                }
            }
        }
        System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());
    }

}