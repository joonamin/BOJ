import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp1 = new int[N], dp2 = new int[N];
        dp1[0] = dp2[0] = 1;
        for (int i = 1; i < N; i++) {
            if (v[i] >= v[i - 1]) {
                dp1[i] = dp1[i - 1] + 1;
            } else {
                dp1[i] = 1;
            }
            if (v[i] <= v[i - 1]) {
                dp2[i] = dp2[i - 1] + 1;
            } else {
                dp2[i] = 1;
            }
        }
        System.out.println(Math.max(Arrays.stream(dp1).max().getAsInt(), Arrays.stream(dp2).max().getAsInt()));
    }
}