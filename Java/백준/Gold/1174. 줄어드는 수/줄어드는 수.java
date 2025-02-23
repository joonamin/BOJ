import java.io.*;
import java.util.*;

public class Main {
    static int N, ans = -1;
    static String lastString = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Deque<String> dq = new ArrayDeque<>();
        dq.add("");
        while (!dq.isEmpty() && N > 0) {
            String current = dq.poll();
            for (char ch = '0'; ch <= '9' && N > 0; ch++) {
                if (!current.isEmpty() && current.charAt(current.length() - 1) <= ch) {
                    continue;
                }
                String next = new StringBuilder(current).append(ch).toString();
                N--;
                lastString = next;
                dq.add(next);
            }
        }
        if (N > 0)
            System.out.println(-1);
        else
            System.out.println(lastString);
    }
}