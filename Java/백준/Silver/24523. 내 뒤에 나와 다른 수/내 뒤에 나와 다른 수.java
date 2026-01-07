
import java.io.*;
import java.util.*;

public class Main {

    static int N, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] ans = new int[N];
        ans[N - 1] = -2;
        for (int i = N - 2; i >= 0; i--) {
            if (v[i] != v[i + 1]) {
                ans[i] = i + 1;
            } else {
                ans[i] = ans[i + 1];
            }
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(ans).forEach(e -> sb.append(e + 1).append(" "));
        System.out.println(sb);
    }

}