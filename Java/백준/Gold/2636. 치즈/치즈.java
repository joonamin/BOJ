
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, board[][];
    static Queue<int[]> q;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
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
        q = new ArrayDeque<>();
        visited = new boolean[N][M];
        visited[0][0] = true;
        q.add(new int[] { 0, 0 });
        // 1. (0, 0)에서 출발 -> 다음 녹일 치즈 위치들이 담긴 큐 (없을 경우 종료)
        int ans = 0, prevCheese = 0;
        while (!(q = traverse()).isEmpty()) {
            ans++;
            prevCheese = q.size();
        }
        System.out.println(ans);
        System.out.println(prevCheese);
    }

    private static Queue<int[]> traverse() {
        Queue<int[]> nextq = new ArrayDeque<>();
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < dir.length; d++) {
                int nr = cur[0] + dir[d][0], nc = cur[1] + dir[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc])
                    continue;
                visited[nr][nc] = true;
                if (board[nr][nc] == 1) {
                    board[nr][nc] = 0;
                    nextq.add(new int[] { nr, nc });
                } else {
                    q.add(new int[] { nr, nc });
                }
            }
        }
        return nextq;
    }

}