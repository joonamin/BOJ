
import java.io.*;
import java.util.*;

public class Main {

    static int k, len, ans = 0;
    static int[] w;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        len = (1 << (k + 1));
        w = new int[len];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i < len; i++) {
            w[i] = Integer.parseInt(st.nextToken());
            ans += w[i];
        }
        dfs(1);
        System.out.println(ans);
    }

    static int dfs(int idx) {
        if (idx >= len) {
            return 0;
        }
        int left = dfs(idx * 2) + (idx * 2 < len ? w[idx * 2] : 0);
        int right = dfs(idx * 2 + 1) + (idx * 2 + 1 < len ? w[idx * 2 + 1] : 0);
        int max = Math.max(left, right);
        ans += (max - left) + (max - right);
        return max;
    }

}