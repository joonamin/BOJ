
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, board[][];
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> homes = new ArrayList<>();
    static int ans = 0x3f3f3f3f;
    static List<int[]> selected = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    chickens.add(new int[] { i, j });
                } else if (board[i][j] == 1) {
                    homes.add(new int[] { i, j });
                }
            }
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int count, int idx) {
        if (idx == chickens.size() || count == M) {
            // count개 선택시에 각각의 집에서 가까운 가게들을 계속 더해줌
            int result = 0;
            for (int[] hp : homes) {
                int minDist = 0x3f3f3f3f;
                for (int[] sp : selected) {
                    minDist = Math.min(minDist, Math.abs(hp[0] - sp[0]) + Math.abs(hp[1] - sp[1]));
                }
                result += minDist;
                result = Math.min(0x3f3f3f3f, result);
            }
            ans = Math.min(ans, result);
            return;
        }
        selected.add(new int[] { chickens.get(idx)[0], chickens.get(idx)[1] });
        dfs(count + 1, idx + 1);
        selected.remove(selected.size() - 1);
        dfs(count, idx + 1);
    }

}