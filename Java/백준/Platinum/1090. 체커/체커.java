
import java.io.*;
import java.util.*;

public class Main {
    static int N, x[], y[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        x = new int[N];
        y = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            x[i] = u;
            y[i] = v;
        }

        int[] ans = new int[N];
        for (int L = 1; L <= N; L++) {
            int minMovement = 0x3f3f3f3f;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int r = x[i], c = y[j];
                    int[] dist = new int[N];
                    for (int k = 0; k < N; k++) {
                        dist[k] = Math.abs(r - x[k]) + Math.abs(c - y[k]);
                    }
                    Arrays.sort(dist);
                    int res = 0;
                    for (int m = 0; m < L; m++) {
                        res += dist[m];
                    }
                    minMovement = Math.min(minMovement, res);
                }
            }
            ans[L - 1] = minMovement;
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(ans).forEach(e -> sb.append(e).append(" "));
        System.out.println(sb);
    }

}