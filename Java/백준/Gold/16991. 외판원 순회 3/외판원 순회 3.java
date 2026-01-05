
import java.io.*;
import java.util.*;

public class Main {

    static int N, pos[][];
    static double[][] dist; // {mask, cur}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pos = new int[N][];
        for (int i = 0; i < N; i++) {
            pos[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        dist = new double[(1 << N)][N];
        for (int i = 0; i < (1 << N); i++) {
            Arrays.fill(dist[i], 0x3f3f3f3f);
        }
        // {acdist, cur, visited}
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            if (Double.compare(a[0], b[0]) == 0) {
                if (Double.compare(a[1], b[1]) == 0) {
                    return -Double.compare(a[2], b[2]);
                }
                return Double.compare(a[1], b[1]);
            }
            return Double.compare(a[0], b[0]);
        });
        dist[1][0] = 0;
        pq.add(new double[] { 0, 0, 1 });
        while (!pq.isEmpty()) {
            double[] polled = pq.poll();
            double acdist = polled[0];
            int cur = (int) polled[1], mask = (int) polled[2];
            if (acdist > dist[mask][cur])
                continue;
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    continue;
                }
                int nMask = mask | (1 << i);
                double cost = getDist(cur, i);
                if (dist[nMask][i] <= acdist + cost) {
                    continue;
                }
                dist[nMask][i] = acdist + cost;
                pq.add(new double[] { dist[nMask][i], i, nMask });
            }
        }
        double ans = 0x3f3f3f3f;
        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, dist[(1 << N) - 1][i] + getDist(i, 0));
        }
        System.out.println(ans);
    }

    private static double getDist(int p1, int p2) {
        int l1 = (pos[p1][0] - pos[p2][0]) * (pos[p1][0] - pos[p2][0]);
        int l2 = (pos[p1][1] - pos[p2][1]) * (pos[p1][1] - pos[p2][1]);
        return Math.sqrt(l1 + l2);
    }
}