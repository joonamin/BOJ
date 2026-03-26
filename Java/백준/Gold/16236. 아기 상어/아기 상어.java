
import java.io.*;
import java.util.*;

public class Main {

    static int N, board[][];
    static boolean[][] visited;
    static final int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

    static class Shark {
        public int size, eaten;

        public Shark() {
            this.size = 2;
            this.eaten = 0;
        }

        public void eat() {
            if (++this.eaten == size) {
                this.size++;
                this.eaten = 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        int[] cur = new int[2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    board[i][j] = 0;
                    cur[0] = i;
                    cur[1] = j;
                }
            }
        }
        Shark shark = new Shark();
        int ans = 0;

        boolean isEnd = false;
        boolean[][] visited = new boolean[N][N];
        while (!isEnd) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[0] == b[0]) {
                    if (a[1] == b[1]) {
                        return Integer.compare(a[2], b[2]);
                    }
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            });
            visited[cur[0]][cur[1]] = true;
            pq.add(new int[] { 0, cur[0], cur[1] });
            int canEatDist = -1;

            while (!pq.isEmpty()) {
                int[] polled = pq.poll();
                int r = polled[1], c = polled[2];
                if (1 <= board[r][c] && board[r][c] <= 6 && board[r][c] < shark.size) {
                    canEatDist = polled[0];
                    board[r][c] = 0;
                    cur = new int[] { r, c };
                    shark.eat();
                    break;
                }
                for (int d = 0; d < dir.length; d++) {
                    int nr = r + dir[d][0], nc = c + dir[d][1];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] > shark.size || visited[nr][nc])
                        continue;

                    visited[nr][nc] = true;
                    if (board[nr][nc] <= shark.size) {
                        pq.add(new int[] { polled[0] + 1, nr, nc });
                    }
                }
            }
            if (canEatDist == -1) {
                isEnd = true;
                continue;
            }
            ans += canEatDist;
        }
        System.out.println(ans);
    }

}