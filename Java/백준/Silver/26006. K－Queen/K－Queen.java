
import java.io.*;
import java.util.*;

public class Main {

    static int N, K, R, C;
    static List<int[]> p;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        p = new ArrayList<>();
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        K = line[1];
        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = line[0];
        C = line[1];
        for (int i = 0; i < K; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int r = line[0], c = line[1];
            p.add(new int[] { r, c });
        }
        System.out.println(solve());
    }

    private static String solve() {
        // 현재 위치 기준 검사, 현재 위치에서 마킹될 경우
        // 체크 또는 체크메이트
        // 아닌 경우 스테일메이트 또는 NONE
        boolean isMarked = p.stream().anyMatch(e -> {
            return e[0] == R || e[1] == C || R + C == e[0] + e[1] || R - C == e[0] - e[1];
        });
        if (isMarked) {
            if (checkOtherAllMarked()) {
                return "CHECKMATE";
            } else {
                return "CHECK";
            }
        } else {
            if (checkOtherAllMarked()) {
                return "STALEMATE";
            } else {
                return "NONE";
            }
        }
    }

    private static boolean checkOtherAllMarked() {
        List<int[]> others = new ArrayList<>();
        for (int d = 0; d < dir.length; d++) {
            int nr = R + dir[d][0], nc = C + dir[d][1];
            if (nr <= 0 || nc <= 0 || nr > N || nc > N) {
                continue;
            }
            others.add(new int[] { nr, nc });
        }
        return others.stream().allMatch(kp -> {
            return p.stream().anyMatch(qp -> {
                return kp[0] == qp[0] || kp[1] == qp[1] || qp[0] + qp[1] == kp[0] + kp[1]
                        || qp[0] - qp[1] == kp[0] - kp[1];
            });
        });
    }

}