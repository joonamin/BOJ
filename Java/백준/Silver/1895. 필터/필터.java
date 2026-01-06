
import java.io.*;
import java.util.*;

public class Main {

    static int R, C, T, board[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = input[0];
        C = input[1];
        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        T = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= R - 3; i++) {
            for (int j = 0; j <= C - 3; j++) {
                // (i,j)가 offset
                list.add(findMedian(i, j));
            }
        }
        System.out.println(list.stream().filter(e -> e >= T).count());
    }

    private static int findMedian(int sy, int sx) {
        List<Integer> list = new ArrayList<>();
        for (int i = sy; i < sy + 3; i++) {
            for (int j = sx; j < sx + 3; j++) {
                list.add(board[i][j]);
            }
        }
        Collections.sort(list);
        return list.get(list.size() / 2);
    }
}