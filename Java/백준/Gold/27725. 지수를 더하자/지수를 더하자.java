
import java.io.*;
import java.util.*;

public class Main {
    static int N, p[];
    static long K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(p);
        K = Long.parseLong(br.readLine());

        long ans = 0L;
        for (int i = 0; i < N; i++) {
            long j = K, cp = p[i];
            while (j >= cp) {
                ans += j / cp;
                cp = cp * p[i];
            }
        }

        System.out.println(ans);
    }

}