import java.util.*;
import java.io.*;

public class Main {
    static int T, A, B;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            sb.append("Case #").append(i).append(": " + solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int solve() {
        int result = 0;
        for (int root = 1; root * root <= B; root++) {
            if (root * root >= A) {
                if (isPalindrome(root) && isPalindrome(root * root)) {
                    result++;
                }
            }
        }
        return result;
    }

    private static boolean isPalindrome(int num) {
        String sNum = String.valueOf(num);
        int len = sNum.length();
        for (int i = 0; i < len; i++) {
            if (sNum.charAt(i) != sNum.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}