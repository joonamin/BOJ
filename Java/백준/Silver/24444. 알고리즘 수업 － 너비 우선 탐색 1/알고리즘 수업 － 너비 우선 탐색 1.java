
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R, visited[];
    static int counter = 1;
    @SuppressWarnings("unchecked")
    static Set<Integer>[] adj = new Set[100001];
    static {
        for (int i = 0; i < 100001; i++) {
            adj[i] = new TreeSet<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        Queue<int[]> q = new ArrayDeque<>();
        visited[R] = 1;
        q.add(new int[] { 0, R });

        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int acdist = polled[0], cur = polled[1];
            for (int next : adj[cur]) {
                if (visited[next] != 0)
                    continue;
                visited[next] = ++counter;
                q.add(new int[] { counter, next });
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);
    }

}