#include <bits/stdc++.h>
using namespace std;
int N, M, S, D;
vector<pair<int,int>> adj[501];
vector<int> previous[501];
int f(){
    // start : S, destination : D
    // dijkstra를 통해 src -> i번째 노드까지의 최단 경로를 찾고자 함.
    // 또한, prev[501] 을 선언하여, 최단 경로로 방문하였을 때 이전 노드를 저장하게 함
    vector<int> dist(N,1e9);

    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<>> PQ;
    dist[S] = 0;
    PQ.push({0, S});

    while(!PQ.empty()){
        auto top = PQ.top(); PQ.pop();
        int acDist = top.first;
        int current = top.second;
        if( acDist < dist[current] )
            continue;
        for(auto info : adj[current]){
            int cost = info.first, next = info.second;
            if( cost < 0 ) continue; // 해당 간선은 존재하지 않는 간선이다.
            if( dist[current] + cost <= dist[next] ) {
                if (dist[current] + cost < dist[next]) {
                    dist[next] = dist[current] + cost;
                    previous[next].clear();
                    previous[next].push_back(current);
                    PQ.push({dist[next], next});
                } else if (dist[current] + cost == dist[next]) {
                    previous[next].push_back(current);
                }
            }
        }
    }

    return dist[D];
}
void deleteEdge(){
    // BFS를 수행하여 dest -> src 까지의 최단 경로를 순회하며 정점을 제거한다.
    // 마스킹하는 방식으로 해보자.
    queue<int> Q;
    bool visited[501][501] = {false, }; // 해당 엣지를 이전에 방문한 적이 있는가?
    Q.push(D);
    while(!Q.empty()){
        int current = Q.front(); Q.pop();
        for(auto prevElem : previous[current]){
            if( visited[prevElem][current] )
                continue;
            for(auto& edge : adj[prevElem]){
                if( edge.second == current ){
                    edge.first = -1;
                }
            }
            visited[prevElem][current] = true;
            Q.push(prevElem);
        }
    }

}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    while(true){
        cin >> N >> M ;
        if( N == 0 && M == 0 )
            break;
        cin >> S >> D;
        for(int i=0; i<N; i++) {
            adj[i].clear();
            previous[i].clear();
        }

        for(int i=0; i<M; i++){
            int u, v, p;
            cin >> u >> v >> p;
            adj[u].push_back({p, v});
        }
        f();
        deleteEdge();
        int res = f();
        res = (res >= 1e9) ? -1 : res;
        cout << res << '\n';
    }

    return 0;
}


