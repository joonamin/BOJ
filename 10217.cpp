#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef tuple<int,int,int> tp;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int T;
    cin >> T;
    while(T--){
        int N, M, K;
        cin >> N >> M >> K;

        vector<tp> adj[105]; // {도착공항번호, 비용, 거리}

        for(int i=0; i<K; i++){
            int u, v, c, d;
            cin >> u >> v >> c >> d;
            adj[u].push_back({v, c, d});
        }

        int dist[105][10005]; // [i]번 노드에 [j]이하의 금액으로 도착할 수 있는 최단 거리
        for(int i=0; i<105; i++){
            for(int j=0; j<10005; j++)
                dist[i][j] = 1e9;
        }

        // {누적 시간, 누적 코스트, 현재 노드}
        priority_queue<tp, vector<tp>, greater<tp>> PQ;
        PQ.push({0, 0, 1});
        for(int i=0; i<=10000; i++)
            dist[1][i] = 0;

        while(!PQ.empty()){
            auto [acTime, acCost, cur] = PQ.top(); PQ.pop();
            for(auto [next, c, d] : adj[cur]){
                if(c + acCost <= M && dist[next][c + acCost] > d + acTime){
                    dist[next][c + acCost] = d + acTime;
                    for(int j = c + acCost + 1; j <= M; j++){
                        dist[next][j] = min(dist[next][j], d + acTime);
                    }
                    PQ.push({acTime + d, acCost + c, next});
                }
            }
        }
        if(dist[N][M] >= 1e9)
            cout << "Poor KCM\n";
        else
            cout << dist[N][M] << '\n';
    }

    return 0;
}
