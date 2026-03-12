
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(dfs(M, 1, -1));
    }

    private static int dfs(int rest, int acsum, int cur) {
        if (rest == 0) {
            return acsum;
        }
        int res = acsum;
        if (0 <= cur + 1 && cur + 1 < N) {
            res = Math.max(res, dfs(rest - 1, acsum + v[cur + 1], cur + 1));
        }
        if (0 <= cur + 2 && cur + 2 < N) {
            res = Math.max(res, dfs(rest - 1, acsum / 2 + v[cur + 2], cur + 2));
        }
        return res;
    }

}