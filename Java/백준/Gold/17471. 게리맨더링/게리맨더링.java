import java.util.*;
import java.io.*;

public class Main {
    static int N, area[];
    static List<int[]>[] adj;

    static boolean checkBipartite(int state) {
        int spaces = 0;
        boolean[] visited = new boolean[N];
        Deque<int[]> dq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;
            spaces += 1;
            visited[i] = true;
            dq.add(new int[] { i });
            int color = ((state & (1 << i)) == 0) ? 0 : 1;
            while (!dq.isEmpty()) {
                int current = dq.poll()[0];
                for (int[] edge : adj[current]) {
                    int next = edge[0];
                    int nextColor = ((state & (1 << next)) == 0) ? 0 : 1;
                    if (visited[next] || color != nextColor) {
                        continue;
                    }
                    visited[next] = true;
                    dq.add(new int[] { next });
                }
            }
        }
        return spaces == 2;
    }

    static int calc(int state) {
        int[] sums = new int[2];
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) == 0) {
                sums[0] += area[i];
            } else {
                sums[1] += area[i];
            }
        }
        return Math.abs(sums[1] - sums[0]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        area = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int tgt = Integer.parseInt(st.nextToken()) - 1;
                adj[i].add(new int[] { tgt });
                adj[tgt].add(new int[] { i });
            }
        }

        int ans = -1;
        for (int i = 0; i < (1 << N); i++) {
            if (checkBipartite(i)) {
                int result = calc(i);
                if (ans == -1 || ans > result) {
                    ans = result;
                }
            }
        }
        System.out.println(ans);
    }
}