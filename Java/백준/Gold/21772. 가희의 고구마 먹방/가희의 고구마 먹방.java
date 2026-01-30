
import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static char[][] board;
    static int[] start;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        start = new int[2];
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();
            board[i] = line;
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'G') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        visited = new boolean[R][C][11];
        int result = dfs(start[0], start[1], 0, 0);
        System.out.println(result);
    }

    private static int dfs(int r, int c, int t, int cnt) {
        if (t == T) {
            return cnt;
        }
        int result = 0;
        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C || board[nr][nc] == '#') {
                continue;
            }
            int ncnt = board[nr][nc] == 'S' ? cnt + 1 : cnt;
            if (visited[nr][nc][ncnt])
                continue;
            char prev = board[nr][nc];
            visited[nr][nc][ncnt] = true;
            board[nr][nc] = '.';
            result = Math.max(result, dfs(nr, nc, t + 1, ncnt));
            board[nr][nc] = prev;
            visited[nr][nc][ncnt] = false;
        }
        return result;
    }
}