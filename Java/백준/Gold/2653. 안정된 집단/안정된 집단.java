
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int n, parent[], rank[], conn[][];
    static int zip;

    static int find(int u) {
        return u == parent[u] ? u : (parent[u] = find(parent[u]));
    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;
        if (rank[u] > rank[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        parent[u] = v;
        if (rank[u] == rank[v]) {
            rank[v]++;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // 서로 좋아하는 사람들끼리의 집합을 구성
        // 이후 싫어하는 집합을 구성하게 될 경우, 같은 집합으로의 사이클이 생길 경우 불안정
        // 집합은 2명 이상, 각 집합에 속한 모든 인원에 대해서 다른 모든 집합의 원소들을 싫어해야함
        conn = new int[n][n];
        zip = n;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                conn[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        parent = IntStream.rangeClosed(0, n + 1).toArray();
        rank = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // 서로 좋아한다면 merge하여 같은 집합으로 만든다
            for (int j = i + 1; j < n; j++) {
                if (conn[i][j] == 0 && conn[j][i] == 0 && union(i, j)) {
                    zip--;
                }
            }
        }
        // 각각에 집합에 포함된 원소들끼리 0-mesh topology를 이루는 지 검사
        // 다른 모든 집합에 포함된 원소들끼리 1-mesh topology를 이루는 지 검사
        // 모든 조건을 만족하지 못하는 경우가 존재할 경우 0을 출력
        Map<Integer, List<Integer>> map = new HashMap<>();
        boolean[] checked = new boolean[n + 1];
        int actual = 0;
        for (int i = 0; i < n; i++) {
            if (checked[i])
                continue;
            actual++;
            checked[i] = true;
            List<Integer> list = map.computeIfAbsent(find(i), k -> new ArrayList<>());
            list.add(i);
            // 2명 이상의 소집합 구성원소들의 index를 저장
            boolean hasMoreTwo = false;
            for (int j = i + 1; j < n; j++) {
                if (find(i) == find(j)) {
                    hasMoreTwo = true;
                    checked[j] = true;
                    list.add(j);
                }
            }
            if (!hasMoreTwo)
                actual--;
        }
        boolean flag = true;
        loop: {
            for (int i = 0; i < n; i++) {
                List<Integer> elems = map.get(find(i));
                if (map.get(find(i)).size() >= 2 && !checkAllConnected(elems)) {
                    flag = false;
                    break loop;
                }
                for (int otherKey : map.keySet()) {
                    if (otherKey != find(i) && map.get(otherKey).size() >= 2) {
                        if (!checkOtherConnected(elems, map.get(otherKey))) {
                            flag = false;
                            break loop;
                        }
                    }
                }
            }
        }
        if (!flag) {
            System.out.println(0);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(actual).append("\n");
            Arrays.fill(checked, false);
            for (int i = 0; i < n; i++) {
                if (checked[i] || map.get(find(i)).size() < 2)
                    continue;
                List<Integer> list = map.get(find(i));
                for (int j = 0; j < list.size(); j++) {
                    checked[list.get(j)] = true;
                    sb.append(list.get(j) + 1).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }

    }

    private static boolean checkOtherConnected(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                int u = a.get(i), v = b.get(j);
                if (conn[u][v] == 0 || conn[v][u] == 0)
                    return false;
            }
        }
        return true;
    }

    private static boolean checkAllConnected(List<Integer> elems) {
        for (int i = 0; i < elems.size(); i++) {
            for (int j = i + 1; j < elems.size(); j++) {
                int u = elems.get(i), v = elems.get(j);
                if (conn[u][v] != 0 || conn[v][u] != 0)
                    return false;
            }
        }
        return true;
    }

}