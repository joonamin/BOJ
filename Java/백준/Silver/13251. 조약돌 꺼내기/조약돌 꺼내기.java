import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int K = Integer.parseInt(br.readLine());

        if (M == 1 || K == 1) {
            System.out.println(1.0);
        } else {
            double ans = 0.0;
            int total = Arrays.stream(input).sum();
            for (int i = 0; i < input.length; i++) {
                if (input[i] < K)
                    continue;
                int restBalls = input[i];
                int restAllBalls = total;
                double prob = 1.0;
                for (int j = 0; j < K; j++) {
                    prob *= (double) restBalls / restAllBalls;
                    restBalls--;
                    restAllBalls--;
                }
                ans += prob;
            }
            System.out.println(ans);
        }

    }
}