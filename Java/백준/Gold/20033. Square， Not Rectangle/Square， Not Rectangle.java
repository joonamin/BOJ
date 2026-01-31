
import java.io.*;
import java.util.*;

public class Main {
    static int N, h[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        h = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 1, l = 1, r = N;
        while (l <= r) {
            int m = (l + r) / 2;
            if (canPlace(m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean canPlace(int w) {
        int result = 0;
        for (int i = 0; i < N; i++) {
            if (h[i] >= w) {
                if (++result == w) {
                    return true;
                }
            } else {
                result = 0;
            }
        }
        return false;
    }

}