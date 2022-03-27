// 5719 -> 메모리 초과...
#include <bits/stdc++.h>
using namespace std;
int N, M, S, D;
bool canNotUse[501][501] = {false,};
int dijkstra(const vector<vector<pair<int,int>>>& adj, int src, int dest){
    vector<int> minDist(N, 1e9);
    vector<vector<int>> minPaths[501];
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> PQ;

    minDist[src] = 0, minPaths[src].push_back({src});
    PQ.push({0, src});
    while(!PQ.empty()){
        auto top = PQ.top(); PQ.pop();
        int acDist = top.first;
        int current = top.second;
        if( acDist > minDist[current] )
            continue;
        for(int i=0; i<adj[current].size(); i++){
            int cost = adj[current][i].first;
            int next = adj[current][i].second;
            if( canNotUse[current][next] ) continue;
            if( minDist[current] + cost <= minDist[next] ) {
                if (minDist[current] + cost < minDist[next]) {
                    minDist[next] = minDist[current] + cost;

                    minPaths[next] = minPaths[current];
                    for(auto& item : minPaths[next])
                        item.push_back(next);
                    PQ.push({minDist[next], next});
                } else if (minDist[current] + cost == minDist[next]) {
                    for(auto item : minPaths[current]) {
                        item.push_back(next);
                        minPaths[next].push_back(item);
                    }

                }
            }
        }

    }
    // minPaths를 이용하여, canNotUse 후처리
    for(const auto& v : minPaths[dest]){
        for(int i=0; i<v.size()-1; i++){
            canNotUse[v[i]][v[i+1]] = true;
        }
    }
    return minDist[dest];
}
int main(void){
    ios_base::sync_with_stdio(false);

    while(true){
        cin >> N >> M;
        if( N == 0 && M == 0 )
            break;
        cin >> S >> D;
        memset(canNotUse, false, sizeof(canNotUse));
        vector<vector<pair<int,int>>> adj(N);
        for(int i=0; i<M; i++){
            int u, v, p;
            cin >> u >> v >> p;
            adj[u].push_back({p, v});
        }
        dijkstra(adj, S, D);
        int ans = dijkstra(adj, S, D);
        if( ans == 1e9 )
            cout << -1 << '\n';
        else
            cout << ans << '\n';
    }

    return 0;
}