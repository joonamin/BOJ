
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[] chs, tgt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chs = br.readLine().toCharArray();
        tgt = br.readLine().toCharArray();

        int first = solve(true);
        int second = solve(false);
        if (first == -1 && second == -1) {
            System.out.println(-1);
        } else if (first != -1 && second == -1) {
            System.out.println(first);
        } else if (first == -1 && second != -1) {
            System.out.println(second);
        } else {
            System.out.println(Math.min(first, second));
        }
    }

    private static int solve(boolean isFirstChanged) {
        char[] cur = Arrays.copyOf(chs, chs.length);
        int result = 0;
        if (isFirstChanged) {
            result = 1;
            cur[0] = flip(cur[0]);
            cur[1] = flip(cur[1]);
        }

        // i-1, i, i+1 에 대해서 다음 상태는 i, i+1, i+2가 됨 (i-1)가 다르다면 무조건 i가 중심이 되는 stage에서
        // 바꿔주어야함
        for (int i = 1; i < N; i++) {
            if (cur[i - 1] != tgt[i - 1]) {
                result++;
                cur[i - 1] = flip(cur, i - 1);
                cur[i] = flip(cur, i);
                if (i + 1 < N)
                    cur[i + 1] = flip(cur, i + 1);
            }
        }

        if (cur[N - 1] != tgt[N - 1]) {
            return -1;
        }

        return result;
    }

    private static char flip(char[] chs, int idx) {
        return flip(chs[idx]);
    }

    private static char flip(char ch) {
        if (ch == '0') {
            return '1';
        }
        return '0';
    }
}