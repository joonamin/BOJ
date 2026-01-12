
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> dq = new ArrayDeque<>();
        int maxP = -1, lastStudent = -1;
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (line[0] == 1) {
                dq.addLast(line[1]);
                if (dq.size() > maxP || (dq.size() == maxP && lastStudent > line[1])) {
                    maxP = dq.size();
                    lastStudent = line[1];
                }
            } else
                dq.pollLast();
        }
        System.out.printf("%d %d\n", maxP, lastStudent);
    }

}