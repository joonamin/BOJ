import java.io.*;
import java.util.*;

public class Main {
    static int N, M, A, B;
    static int[] Xs = new int[101];
    static int[] Ys = new int[101];
    @SuppressWarnings("unchecked")
    static List<Integer>[] adj = new List[101];
    static int[] dist = new int[101];
    static int[] prev = new int[101];

    static {
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        Arrays.fill(dist, 0x3f3f3f3f);
        Arrays.fill(prev, -1);
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            Xs[i] = Integer.parseInt(st.nextToken());
            Ys[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= M; i++) {
            for (int j = i + 1; j <= M; j++) {
                // Xs[i] + (k_i * Ys[i]) = Xs[j] + (k_j * Ys[j]) 의 해 존재 여부 --> 배주 항등식
                // Xs[i] - Xs[j] = k_j * Ys[j] - k_i * Ys[i]
                // ax + by = c 가 해가 존재하기 위해서는 c % gcd(a, b) == 0
                // 특정 범위 내에서 해 존재 판단 필요
                int g = gcd(Ys[i], Ys[j]);
                if (Math.abs(Xs[i] - Xs[j]) % g != 0) {
                    continue;
                }
                
                int step = (Ys[i] > Ys[j]) ? i : j;
                int check = (Ys[i] > Ys[j]) ? j : i;
                boolean intersect = false;
                for (int f = Xs[step]; f <= N; f += Ys[step]) {
                    if (f >= Xs[check] && (f - Xs[check]) % Ys[check] == 0) {
                        intersect = true;
                        break;
                    }
                }
                if (intersect) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= M; i++) {
            if (A >= Xs[i] && (A - Xs[i]) % Ys[i] == 0) {
                dist[i] = 1;
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int curCtx = q.poll();

            for (int nxtCtx : adj[curCtx]) {
                int cost = 1;
                if (dist[nxtCtx] > dist[curCtx] + cost) {
                    prev[nxtCtx] = curCtx;
                    dist[nxtCtx] = dist[curCtx] + cost;
                    q.add(nxtCtx);
                }
            }
        }

        int ansCtx = -1;
        for (int i = 1; i <= M; i++) {
            if (B >= Xs[i] && (B - Xs[i]) % Ys[i] == 0) {
                if (ansCtx == -1 || dist[ansCtx] > dist[i]) {
                    ansCtx = i;
                }
            }
        }

        if (ansCtx == -1 || dist[ansCtx] == 0x3f3f3f3f) {
            System.out.println(-1);
            return;
        }

        System.out.println(dist[ansCtx]);
        StringBuilder sb = new StringBuilder();

        int curCtx = ansCtx;
        List<Integer> trace = new ArrayList<>();

        while (curCtx != -1) {
            trace.add(curCtx);
            curCtx = prev[curCtx];
        }

        for (int i = trace.size() - 1; i >= 0; i--) {
            sb.append(trace.get(i)).append("\n");
        }
        System.out.print(sb);
    }
}
