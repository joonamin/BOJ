
import java.io.*;
import java.util.*;

public class Main {
    static int N, K, next[];
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        next = new int[N];
        for (int i = 0; i < N; i++) {
            next[i] = Integer.parseInt(br.readLine());
        }

        int cur = 0, M = 1;
        visited[cur] = true;
        boolean canReach = true;
        while (true) {
            if (visited[next[cur]]) {
                canReach = false;
                break;
            }
            if (next[cur] == K) {
                break;
            }
            visited[next[cur]] = true;
            cur = next[cur];
            M++;
        }
        if (canReach) {
            System.out.println(M);
        } else {
            System.out.println(-1);
        }
    }

}