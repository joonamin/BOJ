
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static final int[][] dir1 = { { -1, 0 }, { -1, 1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 1 } };
    static final int[][] dir2 = { { -1, -1 }, { -1, 0 }, { 1, -1 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '#') {
                    ans += calc(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    private static int calc(int r, int c) {
        int[][] dir = (r % 2 == 0) ? dir2 : dir1;
        int res = 0;
        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                continue;
            if (board[nr][nc] == '.') {
                res++;
            }
        }
        return res;
    }

}