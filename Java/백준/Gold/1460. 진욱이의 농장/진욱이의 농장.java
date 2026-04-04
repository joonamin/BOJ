import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static int N, M, board[][], dp[][][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            for (int x = X; x < X + L && x < N; x++) {
                for (int y = Y; y < Y + L && y < N; y++) {
                    board[x][y] = F;
                }
            }
        }

        initDp();
        // a*a 크기의 정사각형에서 최대 2종류의 과일을 포함하여 가져갈 수 있는지
        // a의 크기를 기준으로 파라메트릭 서치
        // dp[i][j][k]
        int l = 1, r = N, ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            if (check(m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        System.out.println(ans * ans);
    }

    private static boolean check(int sz) {
        for (int i = 0; i <= N - sz; i++) {
            for (int j = 0; j <= N - sz; j++) {
                // (i, j)가 offset 크기는 정해짐
                // (i, j) <-> (i + sz - 1, j + sz - 1)
                // 1. [i, j] ~ [a][b] 사이에 포함된 과일들의 개수 구해오기
                int counter = 0;
                int r1 = i, c1 = j;
                int r2 = i + sz - 1, c2 = j + sz - 1;

                for (int k = 1; k <= 7; k++) {
                    int c = dp[r2 + 1][c2 + 1][k] - dp[r1][c2 + 1][k] - dp[r2 + 1][c1][k] + dp[r1][c1][k];
                    if (c > 0)
                        counter++;
                }
                // 0이 포함된 경우는 가져갈 수 없음
                boolean containsZero = (dp[r2 + 1][c2 + 1][0] - dp[r1][c2 + 1][0] - dp[r2 + 1][c1][0]
                        + dp[r1][c1][0]) >= 1;
                if (!containsZero && counter <= 2)
                    return true;
            }
        }
        return false;
    }

    private static void initDp() {
        dp = new int[N + 1][N + 1][8];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int fruit = board[i - 1][j - 1];
                for (int k = 0; k <= 7; k++) {
                    dp[i][j][k] = dp[i - 1][j][k] + dp[i][j - 1][k] - dp[i - 1][j - 1][k] + (fruit == k ? 1 : 0);
                }
            }
        }
    }

}
