import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long min = Long.parseLong(input[0]);
        long max = Long.parseLong(input[1]);
        boolean[] check = new boolean[1_000_001];
        for (long i = 2; i * i <= max; i++) {
            long k = (min + i * i - 1) / (i * i);
            // i^2 * k >= min이 되는 first k
            if (i * i >= min && check[(int) (i * i - min)])
                continue;
            while (i * i * k <= max) {
                check[(int) (i * i * k - min)] = true;
                k++;
            }
        }
        int ans = 0;
        for (int i = 0; i <= max - min; i++) {
            if (!check[i])
                ans++;
        }
        System.out.println(ans);
    }
}