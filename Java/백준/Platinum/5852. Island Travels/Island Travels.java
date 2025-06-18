import java.util.*;
import java.io.*;

public class Main {
    static int R, C, k = 0;
    static char[][] board;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static List<int[]>[] positions = new List[17];
    static int[][] dist = new int[17][17];

    private static void dfs(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C || board[nr][nc] != 'X')
                continue;
            board[nr][nc] = (char) (k + '0');
            positions[k].add(new int[] { nr, nc });
            dfs(nr, nc);
        }
    }

    private static void dijkstra(final int startIsland) {
        int[] p = positions[startIsland].get(0);
        int[][] d = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(d[i], 0x3f3f3f3f);
        }
        Arrays.fill(dist[startIsland], 0x3f3f3f3f);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        dist[startIsland][startIsland] = 0;
        d[p[0]][p[1]] = 0;
        pq.add(new int[] { 0, p[0], p[1] });
        while (!pq.isEmpty()) {
            int acDist = pq.peek()[0];
            int y = pq.peek()[1];
            int x = pq.peek()[2];
            pq.poll();
            for (int i = 0; i < 4; i++) {
                int ny = y + dir[i][0], nx = x + dir[i][1];
                int nAcDist = acDist;
                if (ny < 0 || nx < 0 || ny >= R || nx >= C || board[ny][nx] == '.')
                    continue;
                if (board[ny][nx] == 'S') {
                    nAcDist = nAcDist + 1;
                }
                if (d[ny][nx] <= nAcDist) {
                    continue;
                }
                d[ny][nx] = nAcDist;
                pq.add(new int[] { nAcDist, ny, nx });
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != 'S' && board[i][j] != '.') {
                    dist[startIsland][board[i][j] - '0'] = Math.min(dist[startIsland][board[i][j] - '0'], d[i][j]);
                }
            }
        }
    }

    public static int tsp() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int[][] bdist = new int[17][1 << 17];
        for (int i = 0; i < 17; i++)
            Arrays.fill(bdist[i], 0x3f3f3f3f);
        for (int i = 1; i <= k; i++) {
            bdist[i][1 << i] = 0;
            pq.add(new int[] { 0, i, 1 << i });
        }

        while (!pq.isEmpty()) {
            int total = pq.peek()[0];
            int current = pq.peek()[1];
            int visited = pq.peek()[2];
            pq.poll();

            for (int i = 1; i <= k; i++) {
                if ((visited & (1 << i)) != 0)
                    continue;
                if (bdist[i][visited | (1 << i)] <= total + dist[current][i])
                    continue;
                bdist[i][visited | (1 << i)] = total + dist[current][i];
                pq.add(new int[] { total + dist[current][i], i, visited | (1 << i) });
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            ans = Math.min(ans, bdist[i][(1 << (k + 1)) - 2]);
        }
        return ans;
    }

    public static int tsp2(final int total, final int current, final int visited) {
        if (Integer.bitCount(visited) == k) {
            return total;
        }
        int result = 0x3f3f3f3f;
        for (int i = 1; i <= k; i++) {
            if (dist[current][i] != 0x3f3f3f3f && (visited & (1 << i)) == 0)
                result = Math.min(result, tsp2(total + dist[current][i], i, visited | (1 << i)));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < 16; i++) {
            positions[i] = new ArrayList<>();
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = (char) (++k + '0');
                    positions[k].add(new int[] { i, j });
                    dfs(i, j);
                }
            }
        }

        for (int i = 1; i <= k; i++) {
            dijkstra(i);
        }
        System.out.println(tsp());
        // System.out.println(tsp2(0, 0, 0));
    }
}