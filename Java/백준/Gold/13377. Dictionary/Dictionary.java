import java.util.*;
import java.io.*;

public class Main {
    private static Map<String, Integer> order = new HashMap<>();
    private static char[] chs = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' };
    private static int[] counter;
    private static int clock = 0;
    private static StringBuilder sb = new StringBuilder();

    private static void process(int idx) {
        if (idx == 9) {
            order.put(sb.toString(), ++clock);
        }
        for (int i = 0; i < chs.length; i++) {
            if (counter[i] == 0)
                continue;
            sb.append(chs[i]);
            counter[i]--;
            process(idx + 1);
            counter[i]++;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        counter = new int[9];
        Arrays.fill(counter, 1);
        process(0);
        int TC = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            String q = br.readLine();
            sb.append(order.get(q)).append("\n");
        }
        System.out.println(sb.toString());
    }
}