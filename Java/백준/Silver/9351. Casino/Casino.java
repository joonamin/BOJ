import java.io.*;
import java.util.*;

public class Main {

    private static String[] solve(String S) {
        boolean[][] isPalind = new boolean[S.length()][S.length()];
        int maxLength = 2;
        for (int i = 0; i < S.length(); i++) {
            if (i + 1 < S.length() && S.charAt(i) == S.charAt(i + 1)) {
                isPalind[i][i + 1] = true;
            }
            isPalind[i][i] = true;
        }
        for (int sz = 3; sz <= S.length(); sz++) {
            for (int i = 0; i <= S.length() - sz; i++) {
                int j = i + sz - 1;
                if (S.charAt(i) == S.charAt(j) && isPalind[i + 1][j - 1]) {
                    isPalind[i][j] = true;
                    maxLength = sz;
                }
            }
        }

        List<String> results = new ArrayList<>();
        for (int j = S.length() - 1; j >= maxLength - 1; j--) {
            StringBuilder sb = new StringBuilder();
            int i = j - maxLength + 1;
            if (!isPalind[i][j])
                continue;
            for (int k = i; k <= j; k++) {
                sb.append(S.charAt(k));
            }
            results.add(sb.toString());
        }
        return results.toArray(new String[results.size()]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            String S = br.readLine();
            String[] results = solve(S);
            sb.append("Case #").append(tc + ":");
            Arrays.stream(results).forEach(s -> sb.append("\n" + s));
            sb.append("\n");
        }
        System.out.println(sb);
    }
}