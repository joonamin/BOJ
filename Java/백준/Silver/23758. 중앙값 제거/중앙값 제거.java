
import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] v = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(v);
        int ans = 0, m = (N + 1) / 2;
        for (int i = 0; i < m; i++) {
            ans += log2(v[i]);
        }
        System.out.println(ans + 1);
    }

    private static int log2(int value) {
        int res = 0;
        while (value > 1) {
            res++;
            value /= 2;
        }
        return res;
    }

}