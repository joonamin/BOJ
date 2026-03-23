
import java.io.*;
import java.util.*;

public class Main {
    static int M, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] v = new int[4];
        for (int i = 0; i < 4; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        char[][] board = new char[M + v[0] + v[3]][N + v[1] + v[2]];
        for (int i = 0; i < M; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                board[i + v[0]][j + v[1]] = line[j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '\0') {
                    board[i][j] = ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) ? '#' : '.';
                }
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}