
import java.io.*;
import java.util.*;

public class Main {

    static int[][] coord;

    private static long cross(int[] p1, int[] p2) {
        return 1L * p1[0] * p2[1] - 1L * p2[0] * p1[1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        coord = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]), y = Integer.parseInt(tmp[1]);
            coord[i] = new int[] { x, y };
        }

        long ans = 0L;
        for (int i = 0; i < N; i++) {
            ans += cross(coord[i], coord[(i + 1) % N]);
        }
        ans = (ans < 0) ? -ans : ans;
        System.out.println(String.format("%.1f", ans / 2.0));
    }
}