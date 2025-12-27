
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[] S;
    static List<int[]> list;
    static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        N = Integer.parseInt(br.readLine());
        S = br.readLine().toCharArray();
        list = new ArrayList<>();

        list.add(new int[] {0, 0});

        int[] cur = new int[] {0, 0};
        int cd = 2;
        for (int i = 0; i < N; i++) {
            switch (S[i]) {
                case 'L':
                    cd = (cd + 3) % 4;
                    break;
                case 'R':
                    cd = (cd + 1) % 4;  
                    break;
                case 'F':
                    list.add(new int[] {cur[0] + dir[cd][0], cur[1] + dir[cd][1]});
                    cur = new int[] {cur[0] + dir[cd][0], cur[1] + dir[cd][1]};
            }
        }
        
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE, minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        for (int[] pos : list) {
            minY = Math.min(minY, pos[0]);
            minX = Math.min(minX, pos[1]);
            maxY = Math.max(maxY, pos[0]);
            maxX = Math.max(maxX, pos[1]);
        }
        
        char[][] board = new char[maxY - minY + 1][maxX - minX + 1];
        for (char[] b : board) {
            Arrays.fill(b, '#');
        }

        for (int[] pos : list) {
            board[pos[0] - minY][pos[1] - minX] = '.';
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}