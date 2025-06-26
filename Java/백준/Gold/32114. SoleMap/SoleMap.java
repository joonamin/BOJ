import java.io.*;
import java.util.*;

public class Main {
    static int N, M, W[];
    static long[] passing;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        W = new int[N];
        temp = br.readLine().split(" ");
        for (int i = 1; i < N; i++) {
            W[i] = Integer.parseInt(temp[i - 1]);
        }

        passing = new long[N + 2];
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            int x = Integer.parseInt(temp[2]);
            passing[u] += x;
            passing[v] -= x;
        }

        for (int i = 1; i <= N; i++) {
            passing[i] += passing[i - 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N; i++) {
            long result = 0;
            long d = passing[i] / W[i];
            long r = passing[i] % W[i];
            result = d * d * (W[i] - r) + (d + 1) * (d + 1) * r;
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}