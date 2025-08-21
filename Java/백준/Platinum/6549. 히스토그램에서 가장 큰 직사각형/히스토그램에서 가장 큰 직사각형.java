
import java.io.*;
import java.util.*;

public class Main {

    private static long solve(int[] v) {
        // 단조 증가 스택을 유지
        long result = 0;
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i <= v.length; i++) {
            int h = (i == v.length) ? 0 : v[i];
            while (!dq.isEmpty() && v[dq.peek()] > h) {
                // stack.peek()이 최소 높이가 되는 width 구하기
                long sh = 1L * v[dq.pop()];
                int lb = dq.isEmpty() ? -1 : dq.peek();
                result = Math.max(result, (i - (lb + 1)) * sh);
            }
            dq.push(i);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 0)
                break;
            int[] v = Arrays.stream(input, 1, input.length).toArray();
            sb.append(solve(v)).append("\n");
        }
        System.out.println(sb);
    }

}