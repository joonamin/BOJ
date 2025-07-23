
import java.io.*;
import java.util.*;

public class Main {

    static int T, N, M;
    static char[][] board;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int solve() {
        int cur = 0;
        Queue<int[]> q = new ArrayDeque<>();
        int[] start = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'S')
                    start = new int[] { i, j };
            }
        }
        q.add(start);
        boolean flag = false;
        boolean[][] visited = new boolean[N][M];

        while (cur <= T) {
            int rep = q.size();
            while (rep-- > 0) {
                int r = q.peek()[0], c = q.peek()[1];
                if (r == 0 || c == 0 || r == N - 1 || c == M - 1) {
                    flag = true;
                    break;
                }
                q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = r + dir[d][0], nc = c + dir[d][1];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || board[nr][nc] == '1')
                        continue;
                    if (board[nr][nc] == 'U' || board[nr][nc] == 'D' || board[nr][nc] == 'L' || board[nr][nc] == 'R') {
                        // d == 0 && D || d = 1 && U || d = 2 && R || d == 3 && L
                        if (d == 0 && board[nr][nc] != 'D')
                            continue;
                        if (d == 1 && board[nr][nc] != 'U')
                            continue;
                        if (d == 2 && board[nr][nc] != 'R')
                            continue;
                        if (d == 3 && board[nr][nc] != 'L')
                            continue;
                        visited[nr][nc] = true;
                        q.add(new int[] { nr, nc });
                    } else if (board[nr][nc] == '0') {
                        visited[nr][nc] = true;
                        q.add(new int[] { nr, nc });
                    }
                }
            }
            if (flag)
                break;
            cur++;
        }
        return flag ? cur : -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = readInt();
        N = readInt();
        M = readInt();
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        int result = solve();
        if (result == -1)
            System.out.println("NOT POSSIBLE");
        else
            System.out.println(result);
    }

    private static int readInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

}