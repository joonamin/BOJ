import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        Map<Character, Double> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            char ch = (char) ('A' + i);
            int k = Integer.parseInt(br.readLine());
            map.put(ch, (double) k);
        }
        System.out.printf("%.2f", solve(map, s.toCharArray()));
    }

    private static double solve(Map<Character, Double> map, char[] s) throws Exception {
        Deque<Double> stk = new ArrayDeque<>();
        for (int i = 0; i < s.length; i++) {
            if (stk.isEmpty() || ('A' <= s[i] && s[i] <= 'Z')) {
                stk.push(map.get(s[i]));
            } else {
                double op2 = stk.pop();
                double op1 = stk.pop();
                double res = calc(op1, op2, s[i]);
                stk.push(res);
            }
        }
        return stk.pop();
    }

    private static double calc(double a, double b, char oper) throws Exception {
        switch (oper) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        throw new Exception(String.format("not compatible operands: %c\n", oper));
    }
}
