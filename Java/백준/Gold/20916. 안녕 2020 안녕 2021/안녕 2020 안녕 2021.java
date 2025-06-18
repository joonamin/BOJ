import java.io.*;
import java.util.*;

public class Main {

    private static long counting(List<Integer> list, int value) {
        // list가 asc order라는 것을 보장, value 미만인 last_index를 리턴
        int l = 0, r = list.size() - 1, result = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (list.get(m) < value) {
                result = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return result + 1L;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // postfix = prefix -> 202021
        // 20202021, 2020-2021
        List<Integer> possibleCases = new ArrayList<>();
        possibleCases.add(202021);
        possibleCases.add(20202021);
        for (int i = 0; i <= 9; i++) {
            possibleCases.add(202000000 + 2021 + 10000 * i);
        }
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                List<Integer> list = map.computeIfAbsent(v[i], (k) -> new ArrayList<Integer>());
                list.add(i);
            }

            long ans = 0;
            for (int i = 1; i < n; i++) {
                for (int pc : possibleCases) {
                    int target = pc - v[i];
                    // value가 i미만인 것의 개수 카운팅
                    if (map.containsKey(target))
                        ans += counting(map.get(target), i);
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}