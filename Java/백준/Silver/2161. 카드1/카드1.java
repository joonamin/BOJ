
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> dq = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            dq.addLast(i);
        }
        List<Integer> ans = new ArrayList<>();
        while (dq.size() > 1) {
            ans.add(dq.pollFirst());
            dq.addLast(dq.pollFirst());
        }
        ans.add(dq.poll());
        StringBuilder sb = new StringBuilder();
        ans.stream().forEach(e -> sb.append(e).append(" "));
        System.out.println(sb);
    }

}