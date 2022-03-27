#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, K, adj[11][11], dist[11][11];
int BFS(){
    // start : K, {거리, 현재위치, 방문한 노드}
    typedef tuple<int,int,int> tp;
    priority_queue<tp, vector<tp>, greater<tp>> PQ;
    PQ.push({0, K, (1<<K)});
    while(!PQ.empty()){
        int d, cur, visited;
        tie(d, cur, visited) = PQ.top(); PQ.pop();
        if(visited == (1<<N)-1)
            return d;
        for(int i=0; i<N; i++) {
            if(visited & (1<<i))
                continue;
            PQ.push({d + dist[cur][i], i, visited | (1<<i)});
        }
    }
    return -1;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> K;

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            cin >> adj[i][j];
    }

    // 어쨌거나, 각각의 위치에서 나머지 위치로 가는 경로들의 최단 경로
    // 이전의 방문한 곳을 재방문 가능하므로, 각각의 경로들은 독립적이라고 볼 수 있다.
    // N이 최대 10이므로 방문 노드의 가능한 permutation은 10!
    // 각각을 전처리하기만하면 순열 한번당 상수 시간에 처리 가능
    memcpy(dist, adj, sizeof(adj));
    for(int k=0; k<N; k++){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(dist[i][k] + dist[k][j] < dist[i][j])
                    dist[i][j] = dist[i][k] + dist[k][j];
            }
        }
    }

    cout << BFS();
    return 0;
}
