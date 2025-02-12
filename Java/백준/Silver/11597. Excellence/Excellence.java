import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(v);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < v.length / 2; i++) {
            ans = Math.min(ans, v[i] + v[v.length - 1 - i]);
        }
        System.out.println(ans);
    }
}