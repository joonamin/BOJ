import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    static int N, v[], parent[], rank[];

    public static int find(int u) {
        return (parent[u] == u) ? u : (parent[u] = find(parent[u]));
    }

    public static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;
        if (rank[u] > rank[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        if (rank[u] == rank[v]) {
            rank[v]++;
        }
        parent[u] = v;
        return true;
    }

    public static class Pair implements Comparable<Pair> {
        public int current, w;

        public Pair(int current, int w) {
            this.current = current;
            this.w = w;
        }

        @Override
        public int compareTo(Pair p) {
            return Integer.compare(this.w, p.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        parent = IntStream.rangeClosed(0, N).toArray();
        rank = new int[N];

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(new Pair(i, v[i]));
        }

        int cur = 0, e = 0;
        long ans = 0L;
        while (e < N - 1 && !pq.isEmpty()) {
            Pair p = pq.poll();
            if (union(cur, p.current)) {
                ans += (p.w + v[cur]);
                cur = p.current;
                e++;
            }
        }
        System.out.println(ans);
    }
}