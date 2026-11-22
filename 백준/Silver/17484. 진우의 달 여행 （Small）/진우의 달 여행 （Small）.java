import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int[][] dir = {{1, 0}, {1, -1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        // {r, c, pd, cost}
        for (int j = 0; j < M; j++) {
            q.add(new int[] {0, j, -1, board[0][j]});
        }
        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int r = polled[0], c = polled[1], pd = polled[2], cost = polled[3];
            for (int d = 0; d < dir.length; d++) {
                int nr = r + dir[d][0], nc = c + dir[d][1];
                if (nr == N && nc >= 0 && nc < M && d != pd) {
                    min = Math.min(min, cost);
                    continue;
                }
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || d == pd) continue;
                q.add(new int[] {nr, nc, d, cost + board[nr][nc]});
            }
        }
        System.out.println(min);
    }
}
