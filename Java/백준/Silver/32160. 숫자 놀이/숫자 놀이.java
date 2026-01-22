import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Deque<Integer> results = new ArrayDeque<>();

        int current = N - 1;
        while (current > 1) {
            sb.append(current).append(" ").append(current - 1).append("\n");
            results.add(1);
            current -= 2;
        }

        if (current == 1) {
            results.add(1);
        }

        int lastPicked = results.poll();
        while (!results.isEmpty()) {
            int next = results.poll();
            sb.append(lastPicked).append(" ").append(next).append("\n");
            lastPicked = Math.abs(lastPicked - next);
        }

        sb.append(N).append(" ").append(lastPicked).append("\n");
        System.out.println(N - lastPicked);
        System.out.print(sb);
    }
}

/*
 * 1 2 3 4 5
 * 
 * 1 5 -> 2344
 * 2 4 -> 342
 * 2 4 -> 3 2
 * 3 2 -> 1
 * 
 * 1 2 -> 3451
 * 3 4 -> 511
 * 1 1 -> 50
 * 5 0 -> 5
 * 
 * 수는 계속에서 감속하는 방식으로 쌓이므로 최댓값 - (니머지) 를 두면 됨
 * 큐내부에서 차이가 가장 적은 원소 pair 선택 (단 최댓값 포함 제외)
 */
