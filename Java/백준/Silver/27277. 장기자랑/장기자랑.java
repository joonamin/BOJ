
import java.io.*;
import java.util.*;

public class Main {
    static int N, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(v);
        int i = 0, j = N - 2;
        int ans = v[N - 1];
        while (i <= j) {
            ans += v[j] - v[i];
            i++;
            j--;
        }
        System.out.println(ans);
    }
}