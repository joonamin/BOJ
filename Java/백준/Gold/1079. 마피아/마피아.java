import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[][] R;
    static int[] cur;
    static boolean[] isAlive;
    static int isAliveCount;

    public static int dfs(int depth, int night) {
        if (isAliveCount == 1 || !isAlive[K]) {
            return night;
        }
        if (isAliveCount % 2 == 0) {
            // 밤
            int result = night;
            for (int i = 0; i < N; i++) {
                if (!isAlive[i] || i == K)
                    continue;
                isAlive[i] = false;
                isAliveCount--;
                for (int j = 0; j < N; j++) {
                    if (isAlive[j]) {
                        cur[j] += R[i][j];
                    }
                }
                result = Math.max(result, dfs(depth + 1, night + 1));
                for (int j = 0; j < N; j++) {
                    if (isAlive[j]) {
                        cur[j] -= R[i][j];
                    }
                }
                isAliveCount++;
                isAlive[i] = true;
            }
            return result;
        } else {
            // 낮
            int tgtIdx = -1;
            for (int i = 0; i < N; i++) {
                if (isAlive[i] && (tgtIdx == -1 || cur[i] > cur[tgtIdx])) {
                    tgtIdx = i;
                }
            }
            isAlive[tgtIdx] = false;
            isAliveCount--;
            int result = dfs(depth + 1, night);
            isAliveCount++;
            isAlive[tgtIdx] = true;
            return result;
        }
    }

    public static int solve() {
        return dfs(0, 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        R = new int[N][N];
        cur = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            R[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        K = Integer.parseInt(br.readLine());

        isAlive = new boolean[N];
        isAliveCount = N;
        Arrays.fill(isAlive, true);
        System.out.println(solve());
    }
}