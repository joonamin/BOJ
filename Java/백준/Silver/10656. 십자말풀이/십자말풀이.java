import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == '#') {
                    continue;
                }

                boolean isHorizontalStart = (j == 0 || board[i][j - 1] == '#');
                boolean isVerticalStart = (i == 0 || board[i - 1][j] == '#');

                boolean found = false;
                if (isHorizontalStart && j + 2 < C && board[i][j + 1] == '.' && board[i][j + 2] == '.') {
                    found = true;
                }
                if (isVerticalStart && i + 2 < R && board[i + 1][j] == '.' && board[i + 2][j] == '.') {
                    found = true;
                }

                if (found) {
                    ans.add(new int[] { i + 1, j + 1 });
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for (int[] pos : ans) {
            sb.append(pos[0]).append(" ").append(pos[1]).append("\n");
        }
        System.out.print(sb);
    }
}