import java.io.*;
import java.util.*;

public class Main {

    static char[][] board;

    static void draw(int y, int x, int n) {
        if (n == 0) {
            board[y][x] = '*';
            return;
        }

        int subSize = 1 << (n - 1);

        draw(y, x, n - 1);
        draw(y + subSize, x, n - 1);
        draw(y + subSize, x + subSize, n - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int size = 1 << N;
        board = new char[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(board[i], ' ');
        }

        draw(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            String line = String.valueOf(board[i]).stripTrailing();
            sb.append(line).append("\n");
        }
        System.out.print(sb);
    }
}