
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<List<Integer>> prev = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + 1; i++) {
            prev.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
            prev.get(B).add(A);
        }
        X = Integer.parseInt(br.readLine());

        Queue<Integer> q = new ArrayDeque<>();
        q.add(X);
        int ans = 0;
        boolean[] visited = new boolean[N + 1];
        visited[X] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int p : prev.get(cur)) {
                if (visited[p])
                    continue;
                ans++;
                visited[p] = true;
                q.add(p);
            }
        }
        System.out.println(ans);
    }

}