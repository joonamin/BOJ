
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(A);
            Arrays.sort(B);
            int res = 0;

            for (int i = 0; i < N; i++) {
                res += lowerMaxIdx(B, A[i]) + 1;
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    private static int lowerMaxIdx(int[] v, int value) {
        int l = 0, r = v.length - 1, res = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (v[m] < value) {
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }

}