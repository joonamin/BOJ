import java.io.*;
import java.util.*;

public class Main {

    static int N, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] lis = new int[N];
        int[] lds = new int[N];

        for (int i = 0; i < N; i++) {
            lds[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (v[i] < v[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            lis[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (v[i] < v[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int ans = 1;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, lis[i] + lds[i] - 1);
        }
        System.out.println(ans);
    }

}