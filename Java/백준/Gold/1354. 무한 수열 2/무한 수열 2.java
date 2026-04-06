
import java.io.*;
import java.util.*;

public class Main {
    static long N, P, Q, X, Y;
    static StringTokenizer st;
    static Map<Long, Long> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        System.out.println(solve(N));
    }

    private static long solve(long n) {
        if (n <= 0)
            return 1;
        if (map.containsKey(n)) {
            return map.get(n);
        }

        long left = solve(n / P - X);
        long right = solve(n / Q - Y);
        map.put(n, left + right);
        return left + right;
    }

}