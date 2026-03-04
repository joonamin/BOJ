
import java.io.*;
import java.util.*;

public class Main {

    static int N, v[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                v[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[] visited = new boolean[N + 1];
        Map<Integer, Integer> map = new HashMap<>(); // {value, nextIdx}
        int[][] nexts = new int[N + 1][3];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(nexts[i], -1);
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (map.containsKey(v[i][j])) {
                    nexts[i][j] = map.get(v[i][j]);
                }
                map.put(v[i][j], i);
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            ans++;
            Queue<Integer> q = new ArrayDeque<>();
            for (int j = 0; j < 3; j++) {
                if (nexts[i][j] != -1 && !visited[nexts[i][j]]) {
                    visited[nexts[i][j]] = true;
                    q.add(nexts[i][j]);
                }
            }
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int j = 0; j < 3; j++) {
                    if (nexts[cur][j] == -1 || visited[nexts[cur][j]])
                        continue;
                    visited[nexts[cur][j]] = true;
                    q.add(nexts[cur][j]);
                }
            }
        }
        System.out.println(ans);
    }

}