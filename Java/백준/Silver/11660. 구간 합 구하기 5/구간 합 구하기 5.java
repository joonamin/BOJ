import java.util.*;
import java.io.*;

public class Main {
    static int N, M, board[][], acsum[][];

    private static int query(int[] args) {
        int x1 = args[0], y1 = args[1];
        int x2 = args[2], y2 = args[3];
        return acsum[x2][y2] - acsum[x1 - 1][y2] - acsum[x2][y1 - 1] + acsum[x1 - 1][y1 - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        acsum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                acsum[i][j] = board[i][j] + acsum[i - 1][j] + acsum[i][j - 1] - acsum[i - 1][j - 1];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int[] params = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sb.append(query(params)).append("\n");
        }
        System.out.println(sb.toString());
    }
}