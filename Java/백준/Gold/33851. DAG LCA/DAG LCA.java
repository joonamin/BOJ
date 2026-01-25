
import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Main {
    static int n, m, q, dist[][];
    static List<Integer>[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        parents = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            parents[v].add(u);
        }

        dist = new int[n + 1][n + 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int[] du = getDist(u), dv = getDist(v);
            int result = -1;
            for (int j = 1; j <= n; j++) {
                if (du[j] == -1 || dv[j] == -1)
                    continue;
                if (result == -1 || result > Math.max(du[j], dv[j])) {
                    result = Math.max(du[j], dv[j]);
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] getDist(int from) {
        int[] result = new int[n + 1];
        Arrays.fill(result, -1);
        Queue<int[]> q = new ArrayDeque<>();
        result[from] = 0;
        q.add(new int[] { from, 0 });
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int cur = polled[0], acdist = polled[1];
            for (int next : parents[cur]) {
                if (result[next] != -1) {
                    continue;
                }
                result[next] = acdist + 1;
                q.add(new int[] { next, acdist + 1 });
            }
        }
        return result;
    }

}