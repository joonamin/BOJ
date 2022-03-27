#include <bits/stdc++.h>
using namespace std;
int N, M, depth[40001], parent[40001][20], dist[40001][20];
vector<pair<int,int>> adj[40001];
void DFS(int current, int prev, int d){
    depth[current] = d;
    parent[current][0] = prev;
    for(auto info : adj[current]){
        int next = info.second;
        int cost = info.first;
        if( next == prev ) continue;
        dist[next][0] = cost;
        DFS(next, current, d+1);
    }
}
void makeTable(){
    // parent[u][v] = parent[ parent[u][v-1] ][v-1]
    for(int j=1; j<20; j++) {
        for (int i = 1; i <= N; i++) {
            parent[i][j] = parent[parent[i][j - 1]][j - 1];
            dist[i][j] = dist[i][j-1] + dist[parent[i][j-1]][j-1];
        }
    }
}
int LCS(int u, int v){
    if( depth[u] < depth[v] )
        swap(u, v);
    int diff = depth[u] - depth[v];

    for(int i=0; diff; i++){
        if( diff & 1 )
            u = parent[u][i];
        diff >>= 1;
    }
    if(u == v)
        return u;

    for(int i=19; i>=0; i--){
        if( parent[u][i] != parent[v][i] ){
            u = parent[u][i], v = parent[v][i];
        }
    }
    return parent[u][0];
}
int solve(int a, int b){
    // a <-> b 사이의 거리를 출력
    int ret = 0;
    int m = LCS(a, b);
    int aDiff = depth[a] - depth[m], bDiff = depth[b] - depth[m];
    for(int i=0; aDiff; i++){
        if( aDiff & 1 ){
            ret += dist[a][i];
            a = parent[a][i];
        }
        aDiff >>= 1;
    }
    for(int i=0; bDiff; i++){
        if( bDiff & 1 ){
            ret += dist[b][i];
            b = parent[b][i];
        }
        bDiff >>= 1;
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;
    for(int i=0; i<N-1; i++){
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].push_back({c, b});
        adj[b].push_back({c, a});
    }
    // depth, parent, minDist
    DFS(1, -1, 0);
    makeTable();

    cin >> M;
    while(M--){
        int a, b;
        cin >> a >> b;
        cout << solve(a, b) << '\n';
    }

    return 0;
}