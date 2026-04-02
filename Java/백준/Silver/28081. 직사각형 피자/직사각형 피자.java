
import java.io.*;
import java.util.*;

public class Main {
    static int W, H, N, M;
    static long K;
    static int[] y, x, yInter, xInter;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        N = Integer.parseInt(br.readLine());
        y = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            y[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        x = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        yInter = new int[N + 1];
        xInter = new int[M + 1];
        yInter[0] = y[0];
        xInter[0] = x[0];
        for (int i = 1; i < N; i++) {
            yInter[i] = y[i] - y[i - 1];
        }
        for (int i = 1; i < M; i++) {
            xInter[i] = x[i] - x[i - 1];
        }
        yInter[N] = H - y[N - 1];
        xInter[M] = W - x[M - 1];
        Arrays.sort(yInter);
        Arrays.sort(xInter);

        // for (int i = 0; i < Math.max(N + 1, M + 1); i++) {
        // if (i < yInter.length) {
        // System.out.printf("<yInter[%d]>: %d\n", i, yInter[i]);
        // }
        // if (i < xInter.length) {
        // System.out.printf("<xInter[%d]>: %d\n", i, xInter[i]);
        // }
        // }

        long ans = 0L;
        for (int i = 0; i < N + 1; i++) {
            // System.out.printf("yInter[%d] = %d\n", i, yInter[i]);
            int idx = upperBound(xInter, yInter[i]);
            // System.out.printf("-> idx = %d\n", idx);
            ans += (idx + 1);
        }
        System.out.println(ans);
    }

    private static int upperBound(int[] arr, long value) {
        // value * arr[i] > K가 되는 최초의 idx
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (value * arr[m] <= K) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

}