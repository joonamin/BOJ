
import java.io.*;
import java.util.*;

public class Main {
    /*
     * 1. 각각의 집합에서 최대 가치가 되는 선택지들을 선택하되, 가치가 같은 경우 길이가 짧은 것이 우선이 되도록
     * 2. 길이가 같은 경우는 항상 사전순으로 앞에 온 것이 선택되도록
     * 3. (형식) {라인, left, right}
     * 
     * dp를 구성할 때 dp[i] = {value, left, right} := i번째까지 고려했을 때, 보석의 가치가 최대가 되는 경우의
     * right-left 최솟값 && left가 가장 빠른 값
     * 이전것들을 relay할 경우 dp[i][1] = dp[i-1][0] 또는 dp[i-1][1]
     * 이전것들을 relay하지 않을 경우 dp[i][0] = v[i];
     * 
     */
    static class Item {
        public int value, left, right;

        public Item(int value, int left, int right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Item() {
            this.value = -0x3f3f3f3f;
        }
    }

    static Item[][][] dp;
    static int n, v[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        v = new int[n][];
        dp = new Item[n][][];
        for (int i = 0; i < n; i++) {
            int l = Integer.parseInt(br.readLine());
            v[i] = new int[l];
            dp[i] = new Item[l][2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < l; j++) {
                v[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        List<int[]> result = new ArrayList<>(); // {line, sum, left, right}
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int maxsum = -0x3f3f3f3f, l = 0, r = 0;
            dp[i][0][0] = new Item(v[i][0], 0, 0);
            dp[i][0][1] = new Item();
            for (int j = 1; j < v[i].length; j++) {
                // dp[i][j][1] := 이전의 것을 relay하는 경우
                dp[i][j][0] = new Item(v[i][j], j, j);
                if (dp[i][j - 1][1].value == -0x3f3f3f3f || dp[i][j - 1][0].value >= dp[i][j - 1][1].value) {
                    dp[i][j][1] = new Item(dp[i][j - 1][0].value + v[i][j], dp[i][j - 1][0].left, j);
                } else {
                    dp[i][j][1] = new Item(dp[i][j - 1][1].value + v[i][j], dp[i][j - 1][1].left, j);
                }
            }
            for (int j = 0; j < v[i].length; j++) {
                for (int k = 0; k < 2; k++) {
                    if (maxsum < dp[i][j][k].value
                            || (maxsum != -0x3f3f3f3f && maxsum == dp[i][j][k].value
                                    && r - l > dp[i][j][k].right - dp[i][j][k].left)) {
                        maxsum = dp[i][j][k].value;
                        l = dp[i][j][k].left;
                        r = dp[i][j][k].right;
                    }
                }
            }
            result.add(new int[] { i, maxsum, l + 1, r + 1 });
        }
        // result.stream().forEach(
        // e -> System.out.printf("line: %d, sum: %d, left: %d, right: %d\n", e[0],
        // e[1], e[2], e[3]));
        int ans = 0;
        for (int i = 0; i < result.size(); i++) {
            ans += result.get(i)[1];
        }
        for (int i = 0; i < result.size(); i++) {
            int[] e = result.get(i);
            sb.append(e[2]).append(" ").append(e[3]).append("\n");
        }
        System.out.println(ans);
        System.out.println(sb);
    }

}