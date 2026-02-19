
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long ans = 0L;
    static Item[] items;

    static class Item implements Comparable<Item> {

        public int H, A;

        public Item() {

        }

        public Item(int H, int A) {
            this.H = H;
            this.A = A;
        }

        @Override
        public int compareTo(Item o) {
            if (Integer.compare(this.A, o.A) == 0) {
                return Integer.compare(this.H, o.H);
            }
            return Integer.compare(this.A, o.A);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // Ak + H
        // 1일차, n일차까지의 최대 합
        // n-1일차에 특정 나무를 벤다면, 해당 나무의 높이가 0이 됨
        // 즉 H는 초반에 한번만 영향을 줌
        // A가 큰 것은 나중에 자르는 것이 무조건 이득? -> 어차피 모든 나무들을 베어야함, 중복해서 베는 경우는 없음 (높이가 0이 되기에)
        items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            items[i].H = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            items[i].A = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(items);
        for (int i = 0; i < n; i++) {
            ans += items[i].H + items[i].A * (long) i;
        }
        System.out.println(ans);
    }

}