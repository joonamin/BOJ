
import java.io.*;
import java.util.*;

public class Main {

    private static int min(int... numbers) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            result = Math.min(result, numbers[i]);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] v = br.readLine().toCharArray();

        int RTotal = 0, BTotal = 0;
        int RL = 0, RR = 0;
        int BL = 0, BR = 0;
        for (int i = 0; i < N; i++) {
            if (v[i] == 'R')
                RTotal++;
            else
                BTotal++;
        }

        for (int i = 0; i < N && v[0] == v[i]; i++) {
            if (v[0] == 'R')
                RL++;
            else
                BL++;
        }
        for (int i = N - 1; i >= 0 && v[N - 1] == v[i]; i--) {
            if (v[N - 1] == 'R')
                RR++;
            else
                BR++;
        }
        System.out.println(min(RTotal - RL, RTotal - RR, BTotal - BL, BTotal - BR));
    }

}