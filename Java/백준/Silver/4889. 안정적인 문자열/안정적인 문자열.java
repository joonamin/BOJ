
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 1;
        while (true) {
            String s = br.readLine();
            if (s.charAt(0) == '-') {
                break;
            }
            sb.append(String.format("%d. %d\n", T++, solve(s)));
        }
        System.out.println(sb);
    }

    private static int solve(String s) {
        // h1. 항상 열린괄호의 개수는 닫힌 괄호보다 같거나 많도록 유지한다
        // - 이를 위해서 닫힌 괄호가 더 많이 나오는 경우 치환한다.
        // 해당 위치는 반드시 { 가 나와야한다.

        int opened = 0, result = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '{') {
                opened++;
            } else if (ch == '}') {
                if (opened > 0) {
                    opened--;
                } else {
                    result++;
                    opened++;
                }
            }
        }
        assert (opened % 2 == 0);
        result += opened / 2;
        return result;
    }

}