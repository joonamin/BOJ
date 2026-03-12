
import java.io.*;
import java.util.*;

public class Main {
    static int a, b, N, M;
    static List<List<Integer>> nexts = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            nexts.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nexts.get(from).add(to);
            nexts.get(to).add(from);
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(a);
        int depth = 0;
        boolean flag = true;
        boolean[] visited = new boolean[N + 1];
        visited[a] = true;

        for (depth = 0; !q.isEmpty() && flag; depth++) {
            // System.out.printf("[depth]: %d\n", depth);
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                // System.out.printf("cur: %d\n", cur);
                if (cur == b) {
                    flag = false;
                    break;
                }
                for (int next : nexts.get(cur)) {
                    if (visited[next])
                        continue;
                    visited[next] = true;
                    q.add(next);
                }
            }
            if (!flag)
                break;
        }
        if (flag) {
            System.out.println(-1);
        } else {
            System.out.println(depth);
        }
    }

}