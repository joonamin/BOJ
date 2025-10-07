
import java.io.*;
import java.util.*;

public class Main {
    static int N, teamId = 0, input[][];

    /*
     * 가정1) 순위 asc, 키 desc
     * - stage가 넘어갈 때 마다, 순위가 m이상인 room들은 queue에서 제거한다.
     * - i-stage에서 (i-1)-stage의 방들 중 인원의 키가 최대인 방에 들어간다.
     * 반례) 키가 최대인 방에 들어가는 것이 room_size + 1등인 것을 보장할 수 없다.
     * - 키가 더 낮은 원소가 순위를 기준으로 asc 정렬되어있음. 이전에 해당 방에 들어갈 수도 있다는 뜻
     * 
     * 가정2) 키 desc 정렬 이후 방들 중 {인원수 k_i미만인 방들 중 인원수_max}에 들어간다
     * -
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N][];
        for (int i = 0; i < N; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            input[i] = line;
        }
        Arrays.sort(input, (a, b) -> {
            return -Integer.compare(a[0], b[0]);
        });

        // {팀id, 인원수}
        TreeSet<int[]> treeSet = new TreeSet<>((a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        for (int i = 0; i < N; i++) {
            // System.out.printf("input: (%d, %d)\n", input[i][0], input[i][1]);
            int[] l = treeSet.lower(new int[] { -1, input[i][1] });
            if (l == null) {
                treeSet.add(new int[] { ++teamId, 1 });
                // System.out.printf("[1] teamId: %d, member: %d\n", teamId, 1);
            } else {
                treeSet.remove(l);
                treeSet.add(new int[] { l[0], l[1] + 1 });
                // System.out.printf("[2] teamId: %d, member: %d\n", l[0], l[1] + 1);
            }
        }
        System.out.println(teamId);
    }

}