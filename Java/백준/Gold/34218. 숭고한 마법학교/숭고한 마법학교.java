
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, A[][], src[], dest[];
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int[][] colors;
    static Map<Integer, List<int[]>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        src = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };
        st = new StringTokenizer(br.readLine());
        dest = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };

        colors = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(colors[i], -1);
        }
        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (colors[i][j] != -1 || A[i][j] == 0)
                    continue;
                colors[i][j] = ++k;
                map.computeIfAbsent(k, key -> new ArrayList<>()).add(new int[] { i, j });
                bfs(i, j, k);
            }
        }

        if (colors[src[0]][src[1]] == colors[dest[0]][dest[1]]) {
            System.out.println(0);
            return;
        }

        int ans = -1;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        for (int[] p1 : map.get(colors[src[0]][src[1]])) {
            visited[p1[0]][p1[1]] = true;
            q.add(new int[] { 0, p1[0], p1[1] });
        }
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int acdist = polled[0], r = polled[1], c = polled[2];
            if (colors[r][c] == colors[dest[0]][dest[1]]) {
                ans = acdist;
                break;
            }
            for (int d = 0; d < dir.length; d++) {
                int nr = r + dir[d][0], nc = c + dir[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc])
                    continue;
                visited[nr][nc] = true;
                q.add(new int[] { acdist + 1, nr, nc });
            }
        }

        System.out.println(ans);
    }

    private static void bfs(int sy, int sx, int color) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { sy, sx });
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int y = polled[0], x = polled[1];
            for (int d = 0; d < dir.length; d++) {
                int ny = y + dir[d][0], nx = x + dir[d][1];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || colors[ny][nx] != -1)
                    continue;
                if (A[ny][nx] == 0)
                    continue;
                colors[ny][nx] = color;
                map.get(color).add(new int[] { ny, nx });
                q.add(new int[] { ny, nx });
            }
        }
    }

}