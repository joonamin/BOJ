import java.util.*;
import java.io.*;

public class Main {
    static int N, M, D, initBoard[][];
    static final int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 } };

    private static class Pair {
        public int r, c, d;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
            this.d = -1;
        }

        public Pair(int r, int c, int d) {
            this(r, c);
            this.d = d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pair) {
                Pair p = (Pair) o;
                return p.r == this.r && p.c == this.c;
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        initBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                initBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        // 궁수 배치 (N, 0) ~ (N, M - 1)
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                for (int k = j + 1; k < M; k++) {
                    int count = game(i, j, k);
                    ans = Math.max(ans, count);
                }
            }
        }

        System.out.println(ans);
    }

    private static int game(int c1, int c2, int c3) {
        int result = 0;
        Queue<Pair> giants = new ArrayDeque<>();
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = initBoard[i][j];
                if (initBoard[i][j] == 1) {
                    giants.add(new Pair(i, j));
                }
            }
        }
        boolean isGameEnd = giants.isEmpty();
        while (!isGameEnd) {
            // c1, c2, c3 기준으로 가장 가까운 거인 제거, 제거된 리스트에 있는 giants는 이동시키지 않음
            // 이후 board 갱신
            // board상에서 bfs 진행
            Set<Pair> set = new HashSet<>();
            set.add(getErasedPos(board, c1));
            set.add(getErasedPos(board, c2));
            set.add(getErasedPos(board, c3));

            board = new int[N][M];
            int size = giants.size();
            for (int i = 0; i < size; i++) {
                Pair giant = giants.poll();
                if (set.contains(giant)) {
                    result++;
                    continue;
                } else if (giant.r + 1 >= N) {
                    continue;
                }
                board[giant.r + 1][giant.c] = 1;
                giants.add(new Pair(giant.r + 1, giant.c));
            }

            if (giants.isEmpty()) {
                isGameEnd = true;
            }
        }
        return result;
    }

    private static Pair getErasedPos(int[][] board, int c1) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.d == b.d) {
                return Integer.compare(a.c, b.c);
            }
            return Integer.compare(a.d, b.d);
        });
        pq.add(new Pair(N, c1, 0));
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (p.r != N && board[p.r][p.c] == 1) {
                return new Pair(p.r, p.c);
            }
            for (int d = 0; d < dir.length; d++) {
                int nr = p.r + dir[d][0], nc = p.c + dir[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || p.d + 1 > D)
                    continue;
                pq.add(new Pair(nr, nc, p.d + 1));
            }
        }
        return null;
    }

}