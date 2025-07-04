import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] v = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            int s = Integer.parseInt(temp[0]);
            int e = Integer.parseInt(temp[1]);
            v[i] = new int[] { s, e };
        }
        Arrays.sort(v, (int[] a, int[] b) -> Integer.compare(a[1], b[1]));
        int current = 0, ans = 0;
        for (int i = 0; i < N; i++) {
            if (current > v[i][0]) {
                continue;
            }
            ans += 1;
            current = v[i][1];
        }
        System.out.println(ans);
    }
}