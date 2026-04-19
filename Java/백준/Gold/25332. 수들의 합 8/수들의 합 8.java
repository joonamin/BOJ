
import java.io.*;
import java.util.*;

public class Main {
    static int n, a[], b[];
    static long c[];
    static Map<Long, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        c = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            c[i] = a[i] - b[i];
        }

        long prefix = 0;
        long ans = 0;
        map.put(0L, 1);
        for (int i = 0; i < n; i++) {
            prefix += c[i];
            ans += map.getOrDefault(prefix, 0);
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        System.out.println(ans);
    }

}