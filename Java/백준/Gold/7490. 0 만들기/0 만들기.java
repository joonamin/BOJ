
import java.io.*;
import java.util.*;

public class Main {

    static final char[] operators = { '+', '-', ' ' };
    static List<String> list = null;

    private static long calc(char[] chs) {
        // System.out.println("chs: " + String.valueOf(chs));
        // i ~ i + k
        // i + k + 1이 cal idx
        // i + k + 2 ~ i + k + 2 + m 까지 op2
        int i = 0;
        // [i, j] / [j + 1, j + 1] / [j + 2, j + m]
        // ni = j + m + 1

        long res = 0L;
        while (i < chs.length && (('0' <= chs[i] && chs[i] <= '9') || chs[i] == ' ')) {
            if ('0' <= chs[i] && chs[i] <= '9') {
                res *= 10;
                res += (chs[i] - '0');
            }
            i++;
        }
        // System.out.println("first: " + res);

        while (i < chs.length) {
            // 1. operator 위치는 i가 됨
            int j = i + 1;
            long temp = 0L;
            while (j < chs.length && (('0' <= chs[j] && chs[j] <= '9') || chs[j] == ' ')) {
                if ('0' <= chs[j] && chs[j] <= '9') {
                    temp *= 10;
                    temp += (chs[j] - '0');
                }
                j++;
            }
            // System.out.println("arg: " + temp);
            if (chs[i] == '+') {
                res += temp;
            } else if (chs[i] == '-') {
                res -= temp;
            }

            i = j;
        }
        return res;
    }

    private static void dfs(char[] res, int opIdx) {
        if (opIdx >= res.length) {
            long calcRes = calc(res);
            if (calcRes == 0) {
                list.add(String.valueOf(res));
            }
            return;
        }
        for (char oper : operators) {
            res[opIdx] = oper;
            dfs(res, opIdx + 2);
            res[opIdx] = '\0';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N이 주어졌을 때 operator 수는 N-1
        // N이 최대 <= 9
        // 각각의 operation에서 3개의 case (+, -, ' ')
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            list = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());
            char[] chs = new char[2 * N - 1];
            char tgt = '1';
            for (int i = 0; i < chs.length; i += 2) {
                chs[i] = tgt++;
            }
            dfs(chs, 1);
            Collections.sort(list);
            list.stream().forEach(e -> sb.append(e).append("\n"));
            sb.append("\n");
        }

        System.out.println(sb);
    }

}