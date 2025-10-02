
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long G = Long.parseLong(br.readLine());

        // G = a^2 - b^2 = (a + b)(a - b)
        // a > b
        List<Long> answer = new ArrayList<>();
        for (long i = 1L; i <= G; i++) {
            if (G % i == 0) {
                long c = G / i;
                if ((i + c) % 2 == 0 && (i - c) % 2 == 0) {
                    if ((i + c) / 2 > 0 && (i - c) / 2 > 0) {
                        answer.add((i + c) / 2);
                    }
                }
            }
        }
        if (answer.isEmpty()) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            answer.stream().sorted().forEach(e -> sb.append(e + "\n"));
            System.out.println(sb);
        }
    }

}