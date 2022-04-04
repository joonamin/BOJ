#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, P, K;
vector<pair<int, int>> adj[1005];
typedef long long ll;
ll ans = 1e9;
bool F(ll m){
    // cost 가 > m 인 간선들의 가중치를 1로 두자
    int dist[1005];
    fill(dist, dist+1005, 1e9);
    dist[1] = 0;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> PQ;
    PQ.push({0, 1});
    while(!PQ.empty()) {
        auto [acDist, current] = PQ.top(); PQ.pop();
        for(auto [cost, next] : adj[current]) {
            int realCost = (cost > m);
            if(dist[next] <= dist[current] + realCost)
                continue;
            dist[next] = dist[current] + realCost;
            PQ.push({dist[next], next});
        }
    }
    return dist[N] <= K;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N >> P >> K;
    // K개 지원, 남은 (E - K) 중 max만 지불
    for (int i = 0; i < P; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].push_back({c, b});
        adj[b].push_back({c, a});
    }

    // 1 -> u -> v -> .. -> N
    // dist[u][cost_i] 로 도착하는 모든 경우의 수에 대해서 고려가 필요
    // memoization 불가능 (10^6 * 10^3)

    // F(k) := 1 -> N 까지 가능한 모든 경로 상에 존재하는 edge중 K+1번째 cost의 edge가 k이하인가?
    // Existence 를 보이자

    ll l = 0, r = 1000000;
    while(l <= r){
        ll m = (l + r) / 2;
        if(F(m)){
            ans = m;
            r = m - 1;
        }else
            l = m + 1;
    }

    if(ans == 1e9)
        cout << -1;
    else
        cout << ans;
    return 0;
}
