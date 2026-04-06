
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static StringTokenizer st;
    @SuppressWarnings("unchecked")
    static List<Integer>[] adj = new List[100001];
    static {
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
    }
    static boolean[] hasGom;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        hasGom = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }
        int s = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            int tgt = Integer.parseInt(st.nextToken());
            hasGom[tgt] = true;
        }
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        // 곰곰이와 만나지 않는 경로 => 1 리턴
        // 만약 임의의 만나지 않는 경로가 있을 경우 max로 초기화
        if (dfs(1) == 1) {
            System.out.println("yes");
        } else {
            System.out.println("Yes");
        }
    }

    private static int dfs(int cur) {
        if (dp[cur] != -1) {
            return dp[cur];
        }
        if (adj[cur].isEmpty()) {
            return dp[cur] = hasGom[cur] ? 0 : 1;
        }
        int res = 0; // 모두 만난다고 가정
        for (int nxt : adj[cur]) {
            if (!hasGom[cur] && dfs(nxt) == 1) {
                res = 1;
                break;
            }
        }
        // System.out.printf("cur: %d, res: %d\n", cur, res);
        return dp[cur] = res;
    }

}