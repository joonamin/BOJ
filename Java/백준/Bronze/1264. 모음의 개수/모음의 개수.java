import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while (true) {
            line = br.readLine();
            if (line.equals("#"))
                break;
            int res = 0;
            for (char ch1 : line.toCharArray()) {
                Character ch2 = Character.toLowerCase(ch1);
                if (ch2 == 'a' || ch2 == 'e' || ch2 == 'i' || ch2 == 'o' || ch2 == 'u') {
                    res++;
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

}