import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            map.put(input[0], input[1]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String q = br.readLine();
            sb.append(map.get(q)).append("\n");
        }
        System.out.println(sb.toString());
    }
}