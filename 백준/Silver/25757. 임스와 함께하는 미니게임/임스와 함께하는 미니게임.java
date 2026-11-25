import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        char c = st.nextToken().charAt(0);

        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine().trim();
            set.add(s);
        }

        System.out.println(set.size() / getScale(c));
    }

    private static int getScale(char c) {
        if (c == 'Y') return 1;
        else if (c == 'F') return 2;
        return 3;
    }
}
