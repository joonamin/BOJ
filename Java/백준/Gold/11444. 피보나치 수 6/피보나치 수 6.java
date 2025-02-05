import java.util.*;
import java.io.*;

public class Main {
    private static long n;
    private static final long MOD = 1_000_000_007;
    private static long[][] M = { { 1, 1 }, { 1, 0 } };
    private static final long[][] IDENTITY = { { 1, 0 }, { 0, 1 } };

    private static long[][] multiply(long[][] m1, long[][] m2) {
        long[][] result = new long[m1.length][m2[0].length];
        if (m1[0].length != m2.length)
            throw new RuntimeException("impossible");
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    result[i][j] += (m1[i][k] % MOD) * (m2[k][j] % MOD) % MOD;
                    result[i][j] %= MOD;
                }
            }
        }
        return result;
    }

    private static long[][] power(long[][] m, long k) {
        if (k <= 0)
            return IDENTITY;
        if (k == 1)
            return m;
        long[][] result = IDENTITY;
        if (k % 2 == 0) {
            long[][] sub = power(m, k / 2);
            result = multiply(sub, sub);
        } else {
            result = multiply(m, power(m, k - 1));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = 1;
        while (tc-- > 0) {
            n = Long.parseLong(br.readLine());
            long[][] answer = IDENTITY;

            answer = multiply(power(M, n), new long[][] { { 1 }, { 0 } });
            System.out.println(answer[1][0]);
        }
    }
}