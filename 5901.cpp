#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int N, M, K;
vector<pair<int,int>> adj[10'001];
vector<int> market_pos;
bool isMarket[10'001];

long long dist[5][10'001];
void dijkstra(int start_index) {

    for (int i = 0; i < 10'001; i++) {
        dist[start_index][i] = 1e9;
    }

    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<>> PQ;
    PQ.push({0, market_pos[start_index]});
    dist[start_index][market_pos[start_index]] = 0;

    while (!PQ.empty()) {
        auto [ac_dist, cur] = PQ.top(); PQ.pop();
        for (auto [cost, next] : adj[cur]) {
            if (ac_dist + cost < dist[start_index][next]) {
                dist[start_index][next] = ac_dist + cost;
                PQ.push({dist[start_index][next], next});
            }
        }
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N >> M >> K;
    market_pos.resize(K);
    for (int i = 0; i < K; i++) {
        int market;
        cin >> market;
        market--;
        isMarket[market] = true;
        market_pos[i] = market;
    }

    for (int i = 0; i < M; i++) {
        int a, b, l;
        cin >> a >> b >> l;
        a--, b--;
        adj[a].emplace_back(l, b);
        adj[b].emplace_back(l, a);
    }

    // i번째 마켓에서 특정 노드로 가는 최단 경로를 구한다
    for (int i = 0; i < K; i++) {
        dijkstra(i);
    }

    long long ans = 1e9;
    // k1 -> k2 -> k3 ... -> ka 에 대한 permutation을 모두 고려 5!
    vector<int> permutation;
    for (int i = 0; i < K; i++) { permutation.push_back(i); }
    do {

        long long traverse_cost = 0L; // 마을 사이를 모두 순회하기 위해 필요한 비용
        for (int i = 0; i < K - 1; i++) {
            traverse_cost += dist[permutation[i]][market_pos[permutation[i + 1]]];
        }

        for (int i = 0; i < N; i++) {
            if (!isMarket[i]) {
                ans = min(ans, dist[permutation[0]][i] + traverse_cost + dist[permutation[K - 1]][i]);
            }
        }

    } while (next_permutation(permutation.begin(), permutation.end()));

    cout << ans;
    return 0;
}
