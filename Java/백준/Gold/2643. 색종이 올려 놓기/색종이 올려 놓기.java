
import java.io.*;
import java.util.*;

public class Main {
    static int N, v[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            v[i][0] = Math.max(a, b);
            v[i][1] = Math.min(a, b);
        }

        Arrays.sort(v, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(b[0], a[0]);
        });

        int[] lis = new int[N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (v[j][1] >= v[i][1]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
            ans = Math.max(ans, lis[i]);
        }
        System.out.println(ans);
    }
}
