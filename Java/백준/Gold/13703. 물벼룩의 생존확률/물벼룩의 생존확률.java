
import java.io.*;
import java.util.*;

public class Main {

    static int K, N;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        K = input[0];
        N = input[1];
        // i번째 입력까지 위치가 m인 경우, (m <= 64)
        // m = 0일 때 위로 가는 경우의 수는 m = 1, m = 0에서 전이되는 경우의 수 밖에 없음
        // 초기값은 m = k임
        if (K == 0) {
            System.out.println(0);
            return;
        }
        dp = new long[N + 1][K + N + 2];

        dp[0][K] = 1L;
        long totalDeath = 0L;
        for (int i = 1; i <= N; i++) {
            long death = dp[i - 1][1]; // 방금 죽는 경우, 뒤에 들어오는 2^{n-i}가지의 operation은 모두 죽은 상태
            if (death > 0) {
                totalDeath += death * (1L << (N - i));
            }
            for (int j = 1; j <= K + i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
            }
        }

        System.out.println((1L << N) - totalDeath);
    }

}