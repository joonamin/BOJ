
import java.io.*;
import java.util.*;

public class Main {
    static int N, S, v[];
    static boolean[] isSelected;

    static int dfs(int cur, int ac) {
        if (cur == -1) {
            return ac == S ? 1 : 0;
        }

        if (v[cur] != 0) {
            int cnt = 0;
            for (int i = cur + 1; i < v.length; i++) {
                if (v[i] > v[cur])
                    cnt++;
            }
            if (ac + cnt > S)
                return 0;
            return dfs(cur - 1, ac + cnt);
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (isSelected[i])
                continue;
            int cnt = 0;
            for (int j = cur + 1; j < v.length; j++) {
                if (v[j] > i)
                    cnt++;
            }
            if (ac + cnt > S)
                continue;
            v[cur] = i;
            isSelected[i] = true;
            result += dfs(cur - 1, ac + cnt);
            isSelected[i] = false;
            v[cur] = 0;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        S = Integer.parseInt(temp[1]);
        isSelected = new boolean[N + 1];

        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.stream(v).forEach(e -> isSelected[e] = true);
        System.out.println(dfs(v.length - 1, 0));
    }

}