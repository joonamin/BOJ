#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>

using namespace std;

int main(void) {
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    int N, M;
    cin >> N >> M;

    vector<vector<pair<int, int>>> adj(N + 1);
    for (int i = 0; i < M; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].push_back({c, b});
        adj[b].push_back({c, a});
    }

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> PQ;
    vector<int> dist(N + 1, 1e9);

    dist[1] = 0;
    PQ.push({dist[1], 1});

    while (!PQ.empty()) {
        auto[ac_dist, cur] = PQ.top();
        PQ.pop();

        for (const auto&[cost, next]: adj[cur]) {
            if (dist[next] <= ac_dist + cost)
                continue;
            dist[next] = ac_dist + cost;
            PQ.push({dist[next], next});
        }
    }
    cout << dist[N];
    return 0;
}
