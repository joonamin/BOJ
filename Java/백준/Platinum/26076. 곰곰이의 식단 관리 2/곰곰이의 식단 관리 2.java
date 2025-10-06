
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] stemp = br.readLine().split(" ");
        N = Integer.parseInt(stemp[0]);
        M = Integer.parseInt(stemp[1]);
        board = new char[N + 2][M + 2];
        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(board[i], '1');
        }
        board[0][0] = board[0][1] = board[1][0] = '0';
        board[N + 1][M] = board[N + 1][M + 1] = board[N][M + 1] = '0';

        for (int i = 1; i <= N; i++) {
            stemp = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                board[i][j] = stemp[j - 1].charAt(0);
            }
        }

        // for (int i = 0; i < board.length; i++) {
        // for (int j = 0; j < board[i].length; j++) {
        // System.out.print(board[i][j] + " ");
        // }
        // System.out.println();
        // }

        // {r, c, acdist}
        int[][] dist = new int[N + 2][M + 2];
        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(dist[i], 0x3f3f3f3f);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        dist[2][0] = 0;
        pq.add(new int[] { 2, 0, 0 });
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int r = polled[0], c = polled[1], acdist = polled[2];
            // System.out.printf("r: %d, c : %d, acdist: %d\n", r, c, acdist);
            for (int d = 0; d < dir.length; d++) {
                int nr = r + dir[d][0], nc = c + dir[d][1];
                if (nr >= N + 2 || nc >= M + 2 || nr < 0 || nc < 0)
                    continue;
                if ((nr == 1 && nc == 1) || (nr == N && nc == M))
                    continue;

                int cost = board[nr][nc] == '0' ? 1 : 0;
                if (dist[nr][nc] <= acdist + cost)
                    continue;
                dist[nr][nc] = acdist + cost;
                pq.add(new int[] { nr, nc, acdist + cost });
            }
        }
        System.out.println(dist[0][2]);
    }
}