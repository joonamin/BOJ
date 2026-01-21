
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[][] board;
    static Map<String, Integer> map = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            board[i] = line.toCharArray();
            map.put(line, map.getOrDefault(line, 0) + 1);
        }
        K = Integer.parseInt(br.readLine());

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int zcount = count(board[i]);
            if (zcount > K || zcount % 2 != K % 2)
                continue;
            ans = Math.max(ans, map.get(String.valueOf(board[i])));
        }
        System.out.println(ans);
    }

    private static int count(char[] chs) {
        int result = 0;
        for (char ch : chs) {
            if (ch == '0') {
                result++;
            }
        }
        return result;
    }
}