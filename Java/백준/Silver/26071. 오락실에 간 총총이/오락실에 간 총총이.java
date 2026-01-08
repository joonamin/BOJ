
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<int[]> p;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < line.length; j++) {
                if (line[j] == 'G') {
                    p.add(new int[] { i, j });
                }
            }
        }
        if (p.size() == 1) {
            System.out.println(0);
            return;
        }
        // 서로 다른 x좌표를 가진 곰곰이들은 반드시 왼쪽 벽 또는 오른쪽 벽에서 합쳐짐
        // 서로 다른 y좌표를 가진 곰곰이들은 반드시 위쪽 벽 또는 아래쪽 벽에서 합쳐짐

        int maxY = -1, maxX = -1, minY = 0x3f3f3f3f, minX = 0x3f3f3f3f;
        for (int i = 0; i < p.size(); i++) {
            maxY = Math.max(maxY, p.get(i)[0]);
            maxX = Math.max(maxX, p.get(i)[1]);
            minY = Math.min(minY, p.get(i)[0]);
            minX = Math.min(minX, p.get(i)[1]);
        }
        int l1 = (maxX == minX) ? 0 : Math.min(maxX, N - 1 - minX);
        int l2 = (maxY == minY) ? 0 : Math.min(maxY, N - 1 - minY);
        System.out.println(l1 + l2);
    }

}