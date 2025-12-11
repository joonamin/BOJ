
import java.io.*;
import java.util.*;

public class Main {

    static int N, src[];

    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { 0, 0 } };
    static Map<Integer, List<int[]>> map = new HashMap<>();
    static long[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dist = new long[N + 1][5];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dist[i], 0x3f3f3f3f3f3f3f3fL);
        }

        for (int i = 0; i < N; i++) {
            int[] point = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int d = 0; d < dir.length; d++) {
                int nr = point[0] + dir[d][0];
                int nc = point[1] + dir[d][1];
                if (nr <= 0 || nr > 100000 || nc <= 0 || nc > 100000)
                    continue;
                List<int[]> list = map.computeIfAbsent(i + 1, k -> new ArrayList<>());
                list.add(new int[] { nr, nc });
            }
        }

        // pq = {r, c, dist, idx}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[2], b[2]));
        for (int i = 0; i < dir.length; i++) {
            dist[0][i] = 0;
        }
        pq.add(new long[] { src[0], src[1], 0, 0 });
        while (!pq.isEmpty()) {
            long[] polled = pq.poll();
            long r = polled[0], c = polled[1], acdist = polled[2];
            int idx = (int) polled[3];
            if (idx + 1 > N)
                continue;
            int j = 0;
            for (int[] nextPos : map.get(idx + 1)) {
                int nr = nextPos[0], nc = nextPos[1];
                long d = Math.abs(r - nr) + Math.abs(c - nc);
                if (dist[idx + 1][j] > acdist + d) {
                    dist[idx + 1][j] = acdist + d;
                    pq.add(new long[] { nr, nc, acdist + d, idx + 1 });
                }
                j++;
            }
        }

        System.out.println(Arrays.stream(dist[N]).min().getAsLong());
    }

}