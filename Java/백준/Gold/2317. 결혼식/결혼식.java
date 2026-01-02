
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], K = input[1];
        /*
         * 선제조건은 사자가족들은 모두 순서에 맞추어서 들어감, 이후에 그 사이에 일반 참석자들이 들어가는 방식
         * 사자: 1900, 1300, 1500
         * 참석자: 1200, 1600
         * 배치: [1900] 1600 [1300] 1200 [1500] => 300 + 300 + 100 + 300 = 1000
         * 
         * 중간 사자들 사이에 키가 range내에 있는 참석자를 삽입하는 것은 전체 합에 영향을 끼치지 않음
         * 중간 사자들 사이에 range를 벗어나게 될 경우 전체 합이 증가하는 형식
         * 이를 최소화하기 위해서 사자 가족들 사이에 range를 벗어나는 것들은 bulk로 앞에 배치 및 뒤에 배치, 이 bulk의 끝단에 오는
         * 점들이 이 경로 사이의 크기를 결정
         * 이 점들을 최대, 최소로 잡는 것이 bulk 사이의 산봉우리를 없앰, edge case로 양옆 구간을 벗어나는 경우가 있음
         * 최대보다 작거나 최소보다 작으면 양옆을 고려해야하는 경우가 발생함
         */
        int[] lions = new int[K];
        int lMax = -1, lMin = 0x3f3f3f3f;
        for (int i = 0; i < K; i++) {
            lions[i] = Integer.parseInt(br.readLine());
            lMax = Math.max(lMax, lions[i]);
            lMin = Math.min(lMin, lions[i]);
        }

        int max = -1, min = 0x3f3f3f3f;
        for (int i = 0; i < N - K; i++) {
            int m = Integer.parseInt(br.readLine());
            max = Math.max(max, m);
            min = Math.min(min, m);
        }

        int ans = 0;
        for (int i = 0; i < K - 1; i++) {
            ans += Math.abs(lions[i] - lions[i + 1]);
        }

        if (max > lMax) {
            int c1 = Math.abs(lions[0] - max);
            int c2 = Math.abs(lions[K - 1] - max);
            int c3 = 2 * (max - lMax);
            ans += Math.min(c1, Math.min(c2, c3));
        }
        if (min < lMin) {
            int c1 = Math.abs(lions[0] - min);
            int c2 = Math.abs(lions[K - 1] - min);
            int c3 = 2 * (lMin - min);
            ans += Math.min(c1, Math.min(c2, c3));
        }
        System.out.println(ans);
    }

}