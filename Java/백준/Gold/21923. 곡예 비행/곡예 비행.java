
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, score[][], dp1[][], dp2[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];
        score = new int[N][M];
        for (int i = 0; i < N; i++) {
            score[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp1 = new int[N][M];
        dp2 = new int[N][M];

        dp1[N - 1][0] = score[N - 1][0];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                int maxSub = (i + 1 <= N - 1) ? dp1[i + 1][j] : -0x3f3f3f3f;
                maxSub = (j - 1 >= 0) ? Math.max(maxSub, dp1[i][j - 1]) : maxSub;
                if (maxSub == -0x3f3f3f3f)
                    maxSub = 0;
                dp1[i][j] = maxSub + score[i][j];
            }
        }

        dp2[N - 1][M - 1] = score[N - 1][M - 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                int maxSub = (i + 1 <= N - 1) ? dp2[i + 1][j] : -0x3f3f3f3f;
                maxSub = (j + 1 < M) ? Math.max(maxSub, dp2[i][j + 1]) : maxSub;
                if (maxSub == -0x3f3f3f3f)
                    maxSub = 0;
                dp2[i][j] = maxSub + score[i][j];
            }
        }

        int ans = -0x3f3f3f3f;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, dp1[i][j] + dp2[i][j]);
            }
        }
        System.out.println(ans);

    }

}