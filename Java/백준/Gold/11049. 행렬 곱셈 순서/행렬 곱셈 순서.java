import java.io.*;
import java.util.*;

public class Main {
    static int N, R, C;
    static int[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        v = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            v[i][0] = Integer.parseInt(st.nextToken());
            v[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }

    public static int solve() {
        // 항상 순서대로 연산이 가능한 입력만 들어온다.
        // 임의의 k에 대해서 [k] = [k+1] 의 값은 동일하다
        // [i][j] = [i][k] + [k + 1][j] + vi * vk * vj
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = 0x3f3f3f3f;
                if (i == j)
                    dp[i][j] = 0;
            }
        }
        for (int sz = 2; sz <= N; sz++) {
            for (int i = 0; i <= N - sz; i++) {
                int j = i + sz - 1;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + v[i][0] * v[k][1] * v[j][1]);
                }
            }
        }
        return dp[0][N - 1];
    }
}