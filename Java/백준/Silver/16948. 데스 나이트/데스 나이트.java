
import java.io.*;
import java.util.*;

public class Main {

    static int[][] dist;
    static int N, r1, c1, r2, c2;
    static int[][] dir = { { -2, -1 }, { -2, 1 }, { 0, -2 }, { 0, 2 }, { 2, -1 }, { 2, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(dist[i], 0x3f3f3f3f);
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        // {acdist, r, c}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        dist[r1][c1] = 0;
        pq.add(new int[] { 0, r1, c1 });
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int acdist = polled[0], r = polled[1], c = polled[2];
            if (acdist > dist[r][c])
                continue;
            for (int d = 0; d < dir.length; d++) {
                int nr = r + dir[d][0], nc = c + dir[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                    continue;
                if (dist[nr][nc] <= dist[r][c] + 1)
                    continue;
                dist[nr][nc] = dist[r][c] + 1;
                pq.add(new int[] { acdist + 1, nr, nc });
            }
        }
        int ans = (dist[r2][c2] == 0x3f3f3f3f) ? -1 : dist[r2][c2];
        System.out.println(ans);
    }

}