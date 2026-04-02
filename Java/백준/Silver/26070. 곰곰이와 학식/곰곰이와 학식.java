
import java.io.*;
import java.util.*;

public class Main {
    static int A, B, C, X, Y, Z;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());

        long ans = 0L;
        for (int i = 0; i < 3; i++) {
            int ea, eb, ec;
            ea = Math.min(A, X);
            eb = Math.min(B, Y);
            ec = Math.min(C, Z);

            ans += ea;
            ans += eb;
            ans += ec;

            X -= ea;
            A -= ea;
            Y -= eb;
            B -= eb;
            Z -= ec;
            C -= ec;

            int nx = Z / 3;
            int ny = X / 3;
            int nz = Y / 3;
            X = nx;
            Y = ny;
            Z = nz;
        }
        System.out.println(ans);
    }

}