
import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static long[] a, b;

    // tgt 보다 작거나 같은 수들의 개수
    private static int getCount(long tgt) {
        // a[i] * b[j] < tgt 의 개수
        // b[j] <= tgt / a[i] 를 만족하는 max(j)
        // System.out.println("target: " + tgt);
        int count = 0;
        for (int i = 0; i < N; i++) {
            long v = (tgt) / a[i];
            // System.out.printf("a[%d]: %d, v: %d\n", i, a[i], v);
            int l = 0, r = N - 1;
            int cur = -1;
            while (l <= r) {
                int m = (l + r) / 2;
                if (b[m] <= v) {
                    cur = m;
                    // System.out.printf("m: %d, b[%d]: %d, v: %d\n", m, m, b[m], v);
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            // System.out.println("cur: " + cur);
            count += cur + 1;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] stmps = br.readLine().split(" ");
        N = Integer.parseInt(stmps[0]);
        K = Integer.parseInt(stmps[1]);
        a = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();
        b = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();
        long l = a[0] * b[0], r = a[N - 1] * b[N - 1];
        long ans = -1L;
        while (l <= r) {
            long m = (l + r) / 2;
            int count = getCount(m);
            // System.out.printf("m = %d, count: %d\n", m, count);
            if (count >= K) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        System.out.println(ans);
    }

}