import java.util.*;
import java.io.*;

public class Main {
    static int N, M, board[][], acsum[][];

    private static int query(int[] args) {
        int x1 = args[0] - 1, y1 = args[1] - 1;
        int x2 = args[2] - 1, y2 = args[3] - 1;
        int result = 0;
        for (int i = x1; i <= x2; i++) {
            int prev = (y1 == 0) ? 0 : acsum[i][y1 - 1];
            result += acsum[i][y2] - prev;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        acsum = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    acsum[i][0] = board[i][0];
                } else {
                    acsum[i][j] = acsum[i][j - 1] + board[i][j];
                }
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