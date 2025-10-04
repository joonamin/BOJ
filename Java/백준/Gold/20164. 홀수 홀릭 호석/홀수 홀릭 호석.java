
import java.io.*;

public class Main {
    static int getOddCount(String s) {
        int result = 0;
        for (char ch : s.toCharArray()) {
            if ((ch - '0') % 2 != 0) {
                result++;
            }
        }
        return result;
    }

    private static int dfs(String s, boolean isMax, int depth) {
        // System.out.print(" ".repeat(depth));
        // System.out.printf("s: %s, [%s], [count: %d]\n", s, (isMax ? "max" : "min"),
        // getOddCount(s));
        if (s.length() == 1) {
            return (s.charAt(0) - '0') % 2 == 0 ? 0 : 1;
        }
        if (s.length() == 2) {
            String s1 = s.substring(0, 1);
            String s2 = s.substring(1, 2);
            String ns = String.valueOf(Integer.parseInt(s1) + Integer.parseInt(s2));
            return getOddCount(s) + dfs(String.valueOf(Integer.parseInt(s1) + Integer.parseInt(s2)), isMax, depth + 1);
        }

        int result = getOddCount(s);
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 1; i <= s.length() - 2; i++) {
            for (int j = i + 1; j <= s.length() - 1; j++) {
                String s1 = s.substring(0, i);
                String s2 = s.substring(i, j);
                String s3 = s.substring(j);
                int v1 = Integer.parseInt(s1);
                int v2 = Integer.parseInt(s2);
                int v3 = Integer.parseInt(s3);
                // System.out.printf("split: [%d, %d, %d]\n", v1, v2, v3);
                int sub = dfs(String.valueOf(v1 + v2 + v3), isMax, depth + 1);
                max = Math.max(max, sub);
                min = Math.min(min, sub);
            }
        }
        if (isMax) {
            return result + max;
        }
        return result + min;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int min = dfs(s, false, 0);
        int max = dfs(s, true, 0);
        System.out.printf("%d %d\n", min, max);
    }

}