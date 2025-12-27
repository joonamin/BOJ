import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[] chs;
    static Deque<Character> stk;
    static final char[] mStr = {'p', 'e', 'e', 'k', 's'};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chs = br.readLine().toCharArray();
        stk = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < N; i++) {
            stk.push(chs[i]);
            while (stk.size() >= 5 && check()) {
                ans++;
                stk.push('*');
            }
        }

        while (stk.size() >= 5 && check()) {
            stk.push('*');
            ans++;
        }

        System.out.println(ans);
    }
    
    private static boolean check() {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(stk.pop());
        }

        boolean res = true;
        for (int i = 0; i < 5; i++) {
            if (mStr[i] == 's' && mStr[i] != list.get(i)) {
                res = false;
            } else if (mStr[i] != 's' && (mStr[i] != list.get(i) && list.get(i) != '*')) {
                res = false;
            }
        }

        if (!res) {
            for (int i = 4; i >= 0; i--) {
                stk.push(list.get(i));
            }
        }
        
        return res;
    }
}