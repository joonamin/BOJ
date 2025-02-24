import java.io.*;
import java.util.*;

public class Main {
    static int N, M, indegree[];
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N + 1];
        adj = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int[] params = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 2; j < params.length; j++) {
                int u = params[j - 1];
                int v = params[j];
                adj[u].add(v);
                indegree[v]++;
            }
        }

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                dq.add(i);
            }
        }
        int polledCount = 0;
        while (!dq.isEmpty()) {
            int current = dq.poll();
            polledCount++;
            sb.append(current).append("\n");
            for (int next : adj[current]) {
                if (--indegree[next] == 0) {
                    dq.add(next);
                }
            }
        }
        if (polledCount == N) {
            System.out.println(sb.toString());
        } else {
            System.out.println(0);
        }
    }
}