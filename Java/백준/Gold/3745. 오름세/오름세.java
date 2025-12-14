import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> dp;

    static int lowerBound(int value) {
        int result = -1;
        int l = 0, r = dp.size() - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (dp.get(m) >= value) {
                result = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String nLine;

        // 여러 테스트 케이스 처리 (EOF까지)
        while ((nLine = br.readLine()) != null) {

            nLine = nLine.trim();
            if (nLine.isEmpty()) {
                continue;
            }
            int N = Integer.parseInt(nLine);

            String pricesLine = br.readLine();
            if (pricesLine == null)
                break;

            int[] v = Arrays.stream(pricesLine.trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            dp = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (dp.isEmpty() || dp.get(dp.size() - 1) < v[i]) {
                    dp.add(v[i]);
                } else {
                    int idx = lowerBound(v[i]);
                    dp.set(idx, v[i]);
                }
            }
            sb.append(dp.size()).append("\n");
        }
        System.out.println(sb);
    }
}