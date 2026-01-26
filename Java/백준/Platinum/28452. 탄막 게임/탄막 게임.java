
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T, K;
    static int pos[], bullets[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        pos = new int[2];
        st = new StringTokenizer(br.readLine());
        pos[0] = Integer.parseInt(st.nextToken());
        pos[1] = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        bullets = new int[K][];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            bullets[i] = new int[] { x, y };
        }

        // 좌표 선정
        boolean canAlive = false;
        for (int tx = pos[0] - T; !canAlive && tx <= pos[0] + T; tx++) {
            for (int ty = pos[1] - T; !canAlive && ty <= pos[1] + T; ty++) {
                if (tx < 0 || ty < 0 || tx >= N || ty >= M)
                    continue;
                boolean isAllBulletsReachable = true;
                for (int[] bullet : bullets) {
                    int bulletToPointDist = getDist(bullet[0], bullet[1], tx, ty);
                    if (bulletToPointDist <= T) {
                        isAllBulletsReachable = false;
                        break;
                    }
                }
                if (isAllBulletsReachable) {
                    canAlive = true;
                }
            }
        }
        if (canAlive) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static int getDist(int srcX, int srcY, int dstX, int dstY) {
        return Math.max(Math.abs(srcX - dstX), Math.abs(srcY - dstY));
    }

}