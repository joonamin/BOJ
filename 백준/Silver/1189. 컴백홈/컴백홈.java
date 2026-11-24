import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K;
    static char[][] board;
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static boolean isOutBound(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] chs = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                board[i][j] = chs[j];
            }
        }

        visited = new boolean[R][C];
        visited[R - 1][0] = true;
        System.out.println(dfs(R - 1, 0, 1));
    }

    private static int dfs(int r, int c, int k) {
        if (k == K && r == 0 && c == C - 1) return 1;
        int result = 0;
        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (k + 1 > K || isOutBound(nr, nc) || board[nr][nc] == 'T' || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            result += dfs(nr, nc, k + 1);
            visited[nr][nc] = false;
        }
        return result;
    }
}
