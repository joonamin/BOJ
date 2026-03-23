
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] strs = br.readLine().split(" ");
            char[] arr = new char[strs.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = strs[i].charAt(0);
            }
            Deque<Character> dq = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                if (dq.isEmpty() || dq.peekFirst() >= arr[i]) {
                    dq.addFirst(arr[i]);
                } else {
                    dq.addLast(arr[i]);
                }
            }
            while (!dq.isEmpty()) {
                sb.append(dq.pollFirst());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}