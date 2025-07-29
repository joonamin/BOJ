
import java.io.*;
import java.util.*;

public class Main {
    static int[][] coord;

    private static long cross(int[] p1, int[] p2) {
        return 1L * p1[0] * p2[1] - 1L * p1[1] * p2[0];
    }

    public static void main(String[] args) throws Exception {
        int N = readInt();
        long ans = 0;
        for (int i = 0; i < N; i++) {
            int M = readInt();
            coord = new int[M][];
            for (int j = 0; j < M; j++) {
                int x = readInt(), y = readInt();
                coord[j] = new int[] { x, y };
            }
            long temp = 0L;
            for (int j = 0; j < M; j++) {
                int[] p1 = new int[] { coord[j][0] - coord[0][0], coord[j][1] - coord[0][1] };
                int[] p2 = new int[] { coord[(j + 1) % M][0] - coord[0][0], coord[(j + 1) % M][1] - coord[0][1] };
                temp += cross(p1, p2);
            }
            temp = temp < 0 ? -temp : temp;
            ans += temp;
        }
        System.out.println(ans / 2);
    }

    private static int readInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}
