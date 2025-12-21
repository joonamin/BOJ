import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static String S;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        // 1. 입력 및 그래프 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = br.readLine();

        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // 2. 레벨 단위 동적 그리디 탐색
        StringBuilder ans = new StringBuilder();
        boolean[] visited = new boolean[N + 1];

        // 현재까지 만들어진 최적 경로의 마지막에 위치할 수 있는 노드들의 집합
        List<Integer> frontierNodes = new ArrayList<>();

        // 시작점 초기화
        frontierNodes.add(1);
        visited[1] = true;
        ans.append(S.charAt(0));

        while (!frontierNodes.isEmpty()) {
            List<Integer> nextFrontier = null;
            char maxChar = '\0';

            // 현재 frontier의 모든 노드에서 다음 단계를 탐색
            for (int cur : frontierNodes) {
                for (int next : adj.get(cur)) {
                    if (visited[next]) continue;

                    char charOfNext = S.charAt(next - 1);

                    // 현재까지 찾은 다음 레벨의 최적 문자보다 작으면 무시
                    if (maxChar > charOfNext) continue;

                    // 더 좋은 문자를 찾았으면, 다음 frontier 후보를 리셋
                    if (nextFrontier == null || maxChar < charOfNext) {
                        nextFrontier = new ArrayList<>();
                        maxChar = charOfNext;
                    }
                    // 최적 문자와 동일한 문자를 가진 노드를 다음 frontier 후보에 추가
                    nextFrontier.add(next);
                }
            }

            // 더 이상 진행할 경로가 없으면 종료
            if (nextFrontier == null) break;

            // 최적 문자를 정답에 추가하고, frontier를 다음 단계로 업데이트
            ans.append(maxChar);
            
            List<Integer> newFrontier = new ArrayList<>();
            for (int node : nextFrontier) {
                if (!visited[node]) {
                    visited[node] = true;
                    newFrontier.add(node);
                }
            }
            frontierNodes = newFrontier;
        }

        // 3. 결과 출력
        System.out.println(ans.toString());
    }
}
