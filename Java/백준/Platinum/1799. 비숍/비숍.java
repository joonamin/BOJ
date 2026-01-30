import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static boolean[] v1;
    static boolean[] v2;
    static int[] res = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v1 = new boolean[2 * N];
        v2 = new boolean[2 * N];

        dfs(0, 0, 0, 0);
        dfs(0, 1, 0, 1);

        System.out.println(res[0] + res[1]);
    }

    static void dfs(int r, int c, int cnt, int color) {
        if (c >= N) {
            r++;
            c = (r % 2 == 0) ? color : 1 - color;
        }

        if (r >= N) {
            res[color] = Math.max(res[color], cnt);
            return;
        }

        if (board[r][c] == 1 && !v1[r + c] && !v2[r - c + N]) {
            v1[r + c] = true;
            v2[r - c + N] = true;

            dfs(r, c + 2, cnt + 1, color);

            v1[r + c] = false;
            v2[r - c + N] = false;
        }

        dfs(r, c + 2, cnt, color);
    }
}