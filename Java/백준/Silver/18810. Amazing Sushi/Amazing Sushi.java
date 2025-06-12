import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int x1, y1, x2, y2;
        StringTokenizer st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());

        int odd = 0, a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            if (m % 2 != 0)
                odd++;
            a += m / 2;
            b += m / 2;
        }

        boolean result = false;
        for (int i = 0; i <= odd; i++) {
            int first = a + i, second = b + (odd - i);
            if (x1 <= first && first <= y1 && x2 <= second && second <= y2) {
                result = true;
                break;
            }
        }

        if (result) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}