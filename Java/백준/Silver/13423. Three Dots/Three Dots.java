
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static int T;
    public static Set<Integer> dataSet;

    public static void main(String[] args) throws Exception{
        String testcase = bf.readLine();
        T = Integer.parseInt(testcase);

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(bf.readLine());
            int[] data = new int[N];
            int result = 0;
            dataSet = new HashSet<>();
            String[] input = bf.readLine().split(" ");

            for (int i = 0; i < N; i++) {
                data[i] = Integer.parseInt(input[i]);
                dataSet.add(data[i]);
            }

            Arrays.sort(data);
            
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    int a = i;
                    int b = j;

                    int start = data[a];
                    int end = data[b];
                    int mid = (start + end) / 2;
                    
                    if (start != end && end != mid && start != mid) {
                        //System.out.println("start, mid, end : " + start + " " + mid + " " + end);
                        if ((start + end) % 2 == 0) {
                            if (dataSet.contains(mid))
                                result++;
                        }
                    }
                    
                }
            }

            System.out.println(result);
        }
    }
}
