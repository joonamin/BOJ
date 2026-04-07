
import java.io.*;
import java.util.*;

public class Main {

    static int N, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int[] timeline = new int[1001];
        for (int i = 0; i < N; i++) {
            int K = Integer.parseInt(br.readLine());
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                for (int k = start; k < end; k++)
                    timeline[k]++;
            }
        }

        int ans = -1, ansSum = -1;
        for (int start = 0; start + T <= 1000; start++) {
            int sum = 0;
            for (int i = start; i <= 1000 && i < start + T; i++) {
                sum += timeline[i];
            }
            if (ans == -1 || sum > ansSum) {
                ansSum = sum;
                ans = start;
            }
        }
        System.out.printf("%d %d\n", ans, ans + T);
    }

}