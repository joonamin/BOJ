
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = input[0], C = input[1];
        char[][] board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            int j = C - 2;
            while (j >= 1 && board[i][j] == '.')
                j--;
            if (j >= 1) {
                list.add(new int[] { board[i][j] - '1', C - 2 - j });
            }
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });

        int[] ranks = new int[list.size()];
        int rank = 1, idx = 0;
        while (idx < list.size()) {
            int i = idx;
            ranks[list.get(idx)[0]] = rank;
            while (i < list.size() && list.get(i)[1] == list.get(idx)[1]) {
                ranks[list.get(i)[0]] = rank;
                i++;
            }
            idx = i;
            rank++;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(ranks[i]);
        }
    }

}