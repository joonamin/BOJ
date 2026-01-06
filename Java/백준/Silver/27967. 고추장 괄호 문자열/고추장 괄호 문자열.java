
import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static char[] S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = br.readLine().toCharArray();

        dfs(0);
        System.out.println(sb);
    }

    private static void dfs(int idx) {
        if (sb.length() >= 1) {
            return;
        }
        if (idx == N) {
            if (check()) {
                sb.append(S);
            }
            return;
        }
        if (S[idx] == 'G') {
            S[idx] = '(';
            dfs(idx + 1);
            S[idx] = ')';
            dfs(idx + 1);
        } else {
            dfs(idx + 1);
        }
    }

    private static boolean check() {
        int opened = 0;
        for (int i = 0; i < N; i++) {
            if (S[i] == '(') {
                opened++;
            } else {
                if (--opened < 0) {
                    return false;
                }
            }
        }
        return opened == 0;
    }
}
