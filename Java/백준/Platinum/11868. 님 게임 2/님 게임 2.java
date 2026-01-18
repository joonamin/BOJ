
import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int xors = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            xors ^= Integer.parseInt(st.nextToken());
        }
        if (xors != 0) {
            System.out.println("koosaga");
        } else {
            System.out.println("cubelover");
        }
    }

}