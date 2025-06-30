import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int X = Integer.parseInt(temp[0]);
        int Y = Integer.parseInt(temp[1]);
        long Z = (100L * Y) / X;
        if (X == Y || Z == 99) {
            System.out.println(-1);
        } else {
            long q1 = 1L * X * (Z + 1) - 100L * Y;
            long q2 = 99L - Z;
            long a = 1L * q1 / q2;
            if (100L * (Y + a) / (X + a) == Z) {
                a++;
            }
            System.out.println(a);
        }
    }
}