import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] ans = { -1, 0 };
        for (int i = 0; i < 5; i++) {
            int[] scores = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sum = Arrays.stream(scores).sum();
            if (ans[1] < sum) {
                ans[0] = i + 1;
                ans[1] = sum;
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}