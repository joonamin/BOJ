
import java.io.*;
import java.util.*;

public class Main {

    static char[] s;
    static Map<Character, Integer> cost = Map.of('(', 2, '[', 3, '{', 1);

    static boolean isComplete() {
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 1; i < s.length - 1; i++) {
            if (!stk.isEmpty() && ((stk.peek() == '(' && s[i] == ')') || (stk.peek() == '[' && s[i] == ']'))) {
                stk.pop();
            } else {
                stk.push(s[i]);
            }
        }
        return stk.isEmpty();
    }

    static long dfs(int a, int b, int depth) {
        if (a + 1 == b) {
            return cost.get(s[a]);
        }

        long result = cost.get(s[a]); // 수정
        long sum = 0L;

        int i = a + 1, j = i;
        assert (s[i] == '(' || s[i] == '[');
        while (i < b) {
            int l1 = 0, l2 = 0;
            while (j < b && (i == j || l1 != 0 || l2 != 0)) {
                if (s[j] == '(')
                    l1++;
                else if (s[j] == '[')
                    l2++;
                else if (s[j] == ')')
                    l1--;
                else if (s[j] == ']')
                    l2--;
                j++;
            }
            sum += dfs(i, j - 1, depth + 1);
            i = j;
        }
        return result * sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        s = new char[temp.length + 2];
        s[0] = '{';
        s[temp.length + 1] = '}';

        for (int i = 1; i <= temp.length; i++) {
            s[i] = temp[i - 1];
        }

        // check
        if (!isComplete()) {
            System.out.println(0);
        } else {
            System.out.println(dfs(0, temp.length + 1, 0));
        }
    }

}