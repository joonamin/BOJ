import java.io.*;
import java.util.*;

public class Main {
    static int N, M, board[][];
    static int[][][] dist;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static class Item implements Comparable<Item> {
        public int r, c, z, acdist;

        public Item(int r, int c, int z, int acdist) {
            this.r = r;
            this.c = c;
            this.z = z;
            this.acdist = acdist;
        }

        @Override
        public int compareTo(Item item) {
            return Integer.compare(this.acdist, item.acdist);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dist = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], 0x3f3f3f3f);
            }
        }

        PriorityQueue<Item> pq = new PriorityQueue<>();
        dist[0][0][0] = 0;
        pq.add(new Item(0, 0, 0, 0));
        while (!pq.isEmpty()) {
            Item p = pq.poll();
            if (p.acdist > dist[p.r][p.c][p.z])
                continue;

            for (int d = 0; d < dir.length; d++) {
                int nr = p.r + dir[d][0], nc = p.c + dir[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                    continue;

                // 연속으로 2번 이상 오작교를 탈 수 없음
                if (board[p.r][p.c] != 1 && board[nr][nc] != 1) {
                    continue;
                }

                if (board[nr][nc] == 0 && p.z == 0 && !isIntersection(nr, nc)) {
                    int ndist = p.acdist + getAddedTime(p.acdist, M);
                    if (ndist < dist[nr][nc][1]) {
                        dist[nr][nc][1] = ndist;
                        pq.add(new Item(nr, nc, 1, ndist));
                    }
                } else if (board[nr][nc] >= 1) {
                    // 오작교는 여러 번 건널 수 있음
                    int added = getAddedTime(p.acdist, board[nr][nc]);
                    if (p.acdist + added < dist[nr][nc][p.z]) {
                        dist[nr][nc][p.z] = p.acdist + added;
                        pq.add(new Item(nr, nc, p.z, p.acdist + added));
                    }
                }
            }
        }
        System.out.println(Math.min(dist[N - 1][N - 1][0], dist[N - 1][N - 1][1]));
    }

    private static int getAddedTime(int curTime, int dur) {
        // dur * k 가 curTime + 1 보다 크거나 같은 최소의 k를 구하고 k * dur - curTime 을 리턴
        int k = 1;
        while (k * dur < curTime + 1)
            k++;
        return k * dur - curTime;
    }

    private static boolean isIntersection(int r, int c) {
        if (board[r][c] != 0)
            return false;
        final int[][][] np = { { { r + dir[0][0], c + dir[0][1] }, { r + dir[2][0], c + dir[2][1] } },
                { { r + dir[0][0], c + dir[0][1] }, { r + dir[3][0], c + dir[3][1] } },
                { { r + dir[1][0], c + dir[1][1] }, { r + dir[2][0], c + dir[2][1] } },
                { { r + dir[1][0], c + dir[1][1] }, { r + dir[3][0], c + dir[3][1] } } };
        for (int i = 0; i < 4; i++) {
            int r1 = np[i][0][0], c1 = np[i][0][1];
            int r2 = np[i][1][0], c2 = np[i][1][1];
            if (r1 < 0 || r2 < 0 || c1 < 0 || c2 < 0 || r1 >= N || r2 >= N || c1 >= N || c2 >= N)
                continue;
            if (board[r1][c1] != 1 && board[r2][c2] != 1)
                return true;
        }
        return false;
    }

}