import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], 0x3f3f3f3f);
        }
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.add(new int[] { 0, 0, 0 });
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int broken = polled[0], r = polled[1], c = polled[2];
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                    continue;
                if (dist[nr][nc] <= broken + (board[nr][nc] - '0'))
                    continue;
                dist[nr][nc] = broken + (board[nr][nc] - '0');
                pq.add(new int[] { dist[nr][nc], nr, nc });
            }
        }

        System.out.println(dist[N - 1][M - 1]);
    }
}