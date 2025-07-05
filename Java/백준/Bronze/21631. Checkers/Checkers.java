
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        long a = Long.parseLong(temp[0]);
        long b = Long.parseLong(temp[1]);
        if (a < b && b != 0)
            System.out.println(a + 1);
        else
            System.out.println(b);
    }

}