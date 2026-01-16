
import java.io.*;
import java.util.*;

public class Main {

    /**
     * k라는 수가 N명의 사람들에 대해서 뒤집히는 횟수 = {k의 약수중 1~N 범위에 속한 약수의 수} = {k < N 이므로 약수의 수와
     * 같음}
     * 약수의 수가 홀수개인 것에 대해서 백색의 깃발이 앞에 있음
     * 1,2,...,k,k+1,...,N 까지의 수에 대해서 약수의 수가 홀수인 k의 총 개수
     * 약수의 수가 홀수이려면 완전제곱수여야함 (x^2 = n, 단 x,n은 정수)
     * 1, 4, 9, ...,...floor(sqrt(N)) 개
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println((int) Math.sqrt(N));
    }

}