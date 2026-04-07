
import java.io.*;
import java.util.*;

public class Main {
    static int R, C, N, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(br.readLine());
        v = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(v);
        int[][] board = new int[R][C];
        int idx = 0;

        align: for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i > 0) {
                    while (idx < v.length && v[idx] <= board[i - 1][j]) {
                        idx++;
                    }
                }
                if (idx == v.length)
                    break align;
                board[i][j] = v[idx++];
            }
        }
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != 0) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

}