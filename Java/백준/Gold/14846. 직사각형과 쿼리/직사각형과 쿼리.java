
import java.io.*;
import java.util.*;

public class Main {
    static int N, Q, x1, y1, x2, y2;
    static int[][][] prefix;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prefix = new int[N + 2][N + 2][11];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int value = Integer.parseInt(st.nextToken());
                // prefix[i][j][k] = prefix[i][j-1] + prefix[i-1][j] - prefix[i-1][j-1];
                for (int k = 0; k <= 10; k++) {
                    prefix[i][j][k] = prefix[i][j - 1][k] + prefix[i - 1][j][k] - prefix[i - 1][j - 1][k];
                    if (k == value) {
                        prefix[i][j][k] += 1;
                    }
                }
            }
        }
        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            sb.append(solve(x1, y1, x2, y2)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int r1, int c1, int r2, int c2) {
        // prefix[r2][c2] - prefix[r2][c1-1] - prefix[r1-1][c2] + prefix[r1-1][c1-1]
        int res = 0;
        for (int k = 0; k <= 10; k++) {
            if (prefix[r2][c2][k] - prefix[r2][c1 - 1][k] - prefix[r1 - 1][c2][k] + prefix[r1 - 1][c1 - 1][k] > 0) {
                res++;
            }
        }
        return res;
    }

}