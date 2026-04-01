
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long ansMin = Long.MAX_VALUE, ansMax = Long.MIN_VALUE;
    static String[] opers;
    static boolean[] isSelected = new boolean[10];
    static List<Integer> selected = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        opers = br.readLine().split(" ");

        // N + 1개의 수를 선택
        select(0);

        String format = String.valueOf("%0" + (N + 1) + "d\n");

        System.out.printf(format, ansMax);
        System.out.printf(format, ansMin);

    }

    private static void select(int idx) {
        if (idx == N + 1) {
            if (check()) {
                long res = getRes();
                ansMin = Math.min(ansMin, res);
                ansMax = Math.max(ansMax, res);
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (isSelected[i])
                continue;
            // [0] < [1] < [2]
            // 현재 선택할 수 있는 수만 선택
            if (idx == 0 || (idx >= 1 && selected.get(idx - 1) < i && opers[idx - 1].equals("<"))
                    || (idx >= 1 && selected.get(idx - 1) > i && opers[idx - 1].equals(">"))) {
                isSelected[i] = true;
                selected.add(i);
                select(idx + 1);
                selected.remove(selected.size() - 1);
                isSelected[i] = false;
            }
        }
    }

    private static long getRes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selected.size(); i++) {
            sb.append(selected.get(i));
        }
        return Long.parseLong(sb.toString());
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            int l = selected.get(i);
            int r = selected.get(i + 1);
            if (opers[i].equals("<")) {
                if (l > r)
                    return false;
            } else if (opers[i].equals(">")) {
                if (l < r)
                    return false;
            }
        }
        return true;
    }

}