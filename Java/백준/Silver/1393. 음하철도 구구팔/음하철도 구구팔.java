
import java.io.*;
import java.util.*;

public class Main {
    static int sx, sy;
    static int tx, ty, dx, dy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        tx = Integer.parseInt(st.nextToken());
        ty = Integer.parseInt(st.nextToken());
        dx = Integer.parseInt(st.nextToken());
        dy = Integer.parseInt(st.nextToken());

        int[] p = new int[] { tx, ty };
        if (dx == 0 && dy == 0) {
            System.out.printf("%d %d", tx, ty);
            return;
        }
        int g = gcd(dy, dx);
        dy /= g;
        dx /= g;
        while (true) {
            if (getDist(sx, sy, p[0], p[1]) <= getDist(sx, sy, p[0] + dx, p[1] + dy)) {
                break;
            }
            p[0] += dx;
            p[1] += dy;
        }
        System.out.printf("%d %d", p[0], p[1]);
    }

    private static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    private static int getDist(int srcX, int srcY, int dstX, int dstY) {
        return (srcX - dstX) * (srcX - dstX) + (srcY - dstY) * (srcY - dstY);
    }

}