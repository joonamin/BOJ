#include <bits/stdc++.h>
using namespace std;
int N, K, depth[100001], parent[100001][25], minVal[100001][25], maxVal[100001][25];
vector<pair<int,int>> adj[100001];
void DFS(int current, int prev, int d){
    depth[current] = d;
    parent[current][0] = prev;
    for(auto next : adj[current]){
        if( next.second == prev ) continue;
        minVal[next.second][0] = maxVal[next.second][0] = next.first;
        DFS(next.second, current, d+1);
    }
}
void setParent(){
    // parent[i][j] = parent[ parent[i][j-1] ][j-1]
    for(int j=1; j<25; j++) {
        for (int i = 1; i <= N; i++) {
            if( parent[i][j-1] <= 0 ) continue;
            parent[i][j] = parent[parent[i][j - 1]][j - 1];
        }
    }
    for(int j=1; j<25; j++){
        for(int i=1; i<=N; i++){
            if( parent[i][j-1] <= 0 ) continue;
            maxVal[i][j] = max(maxVal[i][j-1], maxVal[parent[i][j-1]][j-1]);
            minVal[i][j] = min(minVal[i][j-1], minVal[parent[i][j-1]][j-1]);
        }
    }
}
int lca(int u, int v){
    if( depth[u] < depth[v] )
        swap(u, v);
    int diff = depth[u] - depth[v] ;
    for(int i=0; i<25 && diff; i++){
        if( diff & 1 )
            u = parent[u][i];
        diff >>= 1;
    }
    if( u == v )
        return u;
    for(int i=24; i>=0; i--){
        if( parent[u][i] != parent[v][i] ){
            u = parent[u][i], v = parent[v][i];
        }
    }
    return parent[u][0];
}
void solve(int u, int v){
    int m = lca(u, v);
    int uDiff = depth[u] - depth[m], vDiff = depth[v] - depth[m];
    int mx = -1e9, mn = 1e9;
    for(int i=0; i<25 && uDiff; i++){
        if( uDiff & 1 ){
            mn = min(mn, minVal[u][i]);
            mx = max(mx, maxVal[u][i]);
            u = parent[u][i];
        }
        uDiff >>= 1;
    }
    for(int i=0; i<25 && vDiff; i++){
        if( vDiff & 1 ){
            mn = min(mn, minVal[v][i]);
            mx = max(mx, maxVal[v][i]);
            v = parent[v][i];
        }
        vDiff >>= 1;
    }
    cout << mn << ' ' << mx << '\n';
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;

    for(int i=0; i<N-1; i++){
        int A, B, C;
        cin >> A >> B >> C;
        adj[A].push_back({C, B});
        adj[B].push_back({C, A});
    }

    // lca 를 구하기 위해, 전처리 하는 과정
    // 루트 노드는 1로 가정하자, 어차피 트리의 위계는 상관이 없음(상대적)
    DFS(1, -1, 0);
    setParent();

    cin >> K;
    while(K--){
        int u, v;
        cin >> u >> v;
        solve(u, v);
    }


    return 0;
}
