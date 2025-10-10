
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, board[][];
    static boolean[][] visited;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

    private static boolean can(int r, int c) {
        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                continue;
            if (board[nr][nc] != 0 && board[nr][nc] > board[r][c])
                return false;
        }
        return true;
    }

    private static boolean dfs(int r, int c) {
        boolean res = can(r, c);
        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || board[nr][nc] != board[r][c])
                continue;
            visited[nr][nc] = true;
            if (!dfs(nr, nc)) {
                res = false;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] stemp = br.readLine().split(" ");
        N = Integer.parseInt(stemp[0]);
        M = Integer.parseInt(stemp[1]);
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            stemp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(stemp[j]);
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    if (dfs(i, j)) {
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }

}