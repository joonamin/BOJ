
import java.io.*;
import java.util.*;

public class Main {
    static char[] chs;
    static int[] first = new int[500003];
    static int[] firstS = new int[500003];
    static int n, offset;
    static {
        Arrays.fill(first, -1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        chs = br.readLine().toCharArray();
        n = chs.length;
        offset = 2 * n; // 모두 s가 나오면 -2n, 0-based를 위해서 2n을 더해줌
        int prefixSum = 0, prefixS = 0, ans = -1;

        firstS[offset] = first[offset] = 0;
        for (int i = 1; i <= n; i++) {
            if (chs[i - 1] == 'S') {
                prefixSum -= 2;
                prefixS++;
            } else if (chs[i - 1] == 'K') {
                prefixSum += 1;
            }

            int p = prefixSum + offset;

            if (first[p] == -1) {
                first[p] = i;
                firstS[p] = prefixS;
                continue;
            }
            // 구간내 S가 1개 이상 포함되는 조건
            if (prefixS > firstS[p]) {
                ans = Math.max(ans, i - first[p]);
            }
        }
        System.out.println(ans);
    }

}