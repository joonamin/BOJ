
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken()), m = Long.parseLong(st.nextToken());
            sb.append(solve(n, m)).append("\n");
        }
        System.out.println(sb);
    }

    private static long solve(long n, long m) {
        long res = 0;
        while (n > 0) {
            res++;
            n /= 2L;
        }
        res += m;
        return res;
    }
}