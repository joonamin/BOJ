
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // t 시간내에 처리할 수 있는 사람의 수 >= M
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] T = new long[N];
        for (int i = 0; i < N; i++) {
            T[i] = Long.parseLong(br.readLine());
        }
        long l = 1, r = 1_000_000_000_000_000_000L, ans = -1;
        while (l <= r) {
            long m = (l + r) / 2;
            long res = 0;
            for (int i = 0; i < T.length; i++) {
                res += m / T[i];
                if (res >= M)
                    break;
            }
            if (res >= M) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        System.out.println(ans);
    }
}