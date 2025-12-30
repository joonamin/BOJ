
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], K = input[1];
        int[] c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] p = new int[c.length][2];
        for (int i = 1; i <= c.length; i++) {
            p[i - 1] = new int[] { c[i - 1], i };
        }
        Arrays.sort(p, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return -Integer.compare(a[0], b[0]);
        });

        if (p[0][0] > (N + 1) / 2) {
            System.out.println(-1);
            return;
        }

        int[] ans = new int[N];
        int idx = 0, k = 0;
        while (idx < N && k < K) {
            while (idx < N && c[p[k][1] - 1] > 0) {
                ans[idx] = p[k][1];
                idx += 2;
                c[p[k][1] - 1]--;
            }
            if (c[p[k][1] - 1] == 0)
                k++;
            if (k < K && idx >= N) {
                idx = 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(ans).forEach(e -> sb.append(e).append(" "));
        System.out.println(sb);
    }

}