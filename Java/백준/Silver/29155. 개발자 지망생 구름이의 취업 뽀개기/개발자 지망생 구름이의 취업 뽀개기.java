
import java.io.*;
import java.util.*;

public class Main {

    static int N, cap[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 난이도별 정렬, 난이도 사이에서는 증감 폭이 적도록 배치
        N = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            list.add(new ArrayList<>());

        cap = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int k = line[0] - 1, t = line[1];
            list.get(k).add(t);
        }
        for (int i = 0; i < 5; i++) {
            list.get(i).sort(Comparator.naturalOrder());
        }

        int ans = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < cap[i]; j++) {
                if (j > 0) {
                    ans += list.get(i).get(j) - list.get(i).get(j - 1);
                }
                ans += list.get(i).get(j);
            }
            if (i < 4)
                ans += 60;
        }

        System.out.println(ans);
    }
    // (1, 1), (1, 4), (1, 4) - (2, 5), (2, 7)
    // 풀어야하는 문제수 만큼 난이도 1, 2, 3, 4, 5에서 선정해서 지정
    // 같은 난이도에서 두 문제를 푸는데 걸리는 시간의 차이만큼 합산됨

}