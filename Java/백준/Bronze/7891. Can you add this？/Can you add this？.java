
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = readInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String[] temp = br.readLine().split(" ");
            int result = Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int readInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

}