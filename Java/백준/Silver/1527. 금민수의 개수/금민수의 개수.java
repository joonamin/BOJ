import java.util.*;
import java.io.*;

public class Main {
    static String A, B;
    static StringBuilder sb = new StringBuilder();
    static int ans;
    static final char[] chs = { '4', '7' };

    private static void dfs(int depth) {
        if (depth > B.length()) {
            return;
        }
        for (char ch : chs) {
            sb.append(ch);
            String s = sb.toString();
            long ls = Long.parseLong(s);
            if (ls <= Long.parseLong(B) && ls >= Long.parseLong(A)) {
                ans++;
            }
            dfs(depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = st.nextToken();
        B = st.nextToken();
        dfs(0);
        System.out.println(ans);
    }
}