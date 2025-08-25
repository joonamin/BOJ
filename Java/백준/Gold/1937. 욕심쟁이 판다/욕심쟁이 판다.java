
import java.io.*;
import java.util.*;

public class Main {

    final static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int n, board[][], dp[][];

    private static int dfs(int r, int c) {
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        int mx = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n || board[nr][nc] <= board[r][c]) {
                continue;
            }
            mx = Math.max(mx, dfs(nr, nc));
        }
        return dp[r][c] = mx + 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                board[i][j] = temp[j];
            }
        }
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // dp[i][j] = [i][j]부터 시작하여 최대 길이~~
        // 여러 갈림길이 나올 경우 max + 1을 추가
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        System.out.println(ans);
    }

}