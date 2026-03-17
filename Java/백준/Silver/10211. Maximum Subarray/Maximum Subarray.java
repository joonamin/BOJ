
import java.io.*;
import java.util.*;

public class Main {

    static int T, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] v = new int[N];
            for (int i = 0; i < N; i++) {
                v[i] = Integer.parseInt(st.nextToken());
            }
            int res = v[0], ans = v[0];
            for (int i = 1; i < N; i++) {
                res = Math.max(res + v[i], v[i]);
                ans = Math.max(ans, res);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

}