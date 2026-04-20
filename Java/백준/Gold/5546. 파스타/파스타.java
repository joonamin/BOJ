import java.io.*;
import java.util.*;

public class Main {

    static int N, K, v[];
    static int[][][] dp;
    static final int MOD = 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        v = new int[N + 1]; // 기본값 0: 결정되지 않음

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            v[a] = b;
        }

        // dp[일][전날 파스타][오늘 파스타]
        dp = new int[N + 1][4][4];

        // 1일차 초기화
        if (v[1] != 0) {
            dp[1][0][v[1]] = 1;
        } else {
            for (int p = 1; p <= 3; p++) {
                dp[1][0][p] = 1;
            }
        }

        // 2일차부터 N일차까지 진행
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    if (dp[i][j][k] == 0)
                        continue;

                    // i+1일(오늘) 먹을 파스타 결정
                    for (int m = 1; m <= 3; m++) {
                        // 조건 1: 오늘 파스타가 이미 정해진 경우 체크
                        if (v[i + 1] != 0 && v[i + 1] != m)
                            continue;

                        // 조건 2: 3일 연속 같은 파스타인지 체크 (j=0인 경우는 전전날이 없으므로 무시)
                        if (j != 0 && j == k && k == m)
                            continue;

                        dp[i + 1][k][m] = (dp[i + 1][k][m] + dp[i][j][k]) % MOD;
                    }
                }
            }
        }

        int ans = 0;
        for (int j = 0; j <= 3; j++) {
            for (int k = 1; k <= 3; k++) {
                ans = (ans + dp[N][j][k]) % MOD;
            }
        }
        System.out.println(ans);
    }
}
