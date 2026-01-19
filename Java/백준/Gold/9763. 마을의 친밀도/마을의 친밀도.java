
import java.io.*;
import java.util.*;

public class Main {
    static int N, v[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N][];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            v[i] = new int[] { x, y, z };
        }
        // (x2, y2, z2)를 중심점으로 잡으면 독립적인 두 거리로 볼 수 있음
        int ans = 0x3f3f3f3f;
        for (int i = 0; i < N; i++) {
            int firstD = 0x3f3f3f3f, secondD = 0x3f3f3f3f + 1;
            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;
                int d = Math.abs(v[i][0] - v[j][0]) + Math.abs(v[i][1] - v[j][1]) + Math.abs(v[i][2] - v[j][2]);
                if (firstD > d) {
                    secondD = firstD;
                    firstD = d;
                } else if (secondD > d) {
                    secondD = d;
                }
            }
            ans = Math.min(ans, firstD + secondD);
        }
        System.out.println(ans);
    }

}