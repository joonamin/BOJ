import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static double F;
    static String S;

    public static void main(String[] arsg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            F = Double.parseDouble(st.nextToken());
            S = br.readLine();
            sb.append("Case #" + tc + ": " + solve() + "\n");
        }
        System.out.println(sb.toString());
    }

    public static int solve() {
        int[] oneCounts = new int[N];
        oneCounts[0] = (S.charAt(0) == '1') ? 1 : 0;
        for (int i = 1; i < N; i++) {
            oneCounts[i] = oneCounts[i - 1] + (S.charAt(i) == '1' ? 1 : 0);
        }
        double nearestProportion = Double.MAX_VALUE;
        int result = -1;
        for (int l = 1; l <= N; l++) {
            for (int i = 0; i <= N - l; i++) {
                int j = i + l - 1;
                int count = oneCounts[j] - (i >= 1 ? oneCounts[i - 1] : 0);
                double proportion = 1.0 * count / l;
                if (nearestProportion == Double.MAX_VALUE
                        || (Math.abs(F - proportion) < Math.abs(F - nearestProportion)) ||
                        (Math.abs(F - proportion) == Math.abs(F - nearestProportion) && result > i)) {
                    nearestProportion = proportion;
                    result = i;
                }
            }
        }

        return result;
    }
}