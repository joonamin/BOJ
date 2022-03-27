#include <bits/stdc++.h>
using namespace std;
int N, M;
vector<vector<int>> adj, parent;
vector<int> depth;
void setDepthAndFirstParent(int current, int dep, int prev){
    parent[current][0] = prev;
    depth[current] = dep;
    for(auto child : adj[current]){
        if( child == prev ) continue;
        setDepthAndFirstParent(child, dep+1, current);
    }
}
void setParent(){
    // parent[u][i] = parent[parent[u][i-1]][i-1]
    // i의 최댓값 : ceil( log2(N) )
    const int MAX_SIZE = ceil(log2(N)) + 1;
    for(int i=1; i<MAX_SIZE; i++){
        for(int u=2; u<=N; u++){
            if( parent[u][i-1] != -1 )
                parent[u][i] = parent[parent[u][i-1]][i-1];
        }
    }
}
int LCA(int u, int v){
    if( depth[u] < depth[v] )
        swap(u, v);
    int diff = depth[u] - depth[v];
    const int MAX_SIZE = ceil(log2(N)) + 1;
    for(int i=0; i<MAX_SIZE && diff > 0 ; i++){
        if( diff & 1 )
            u = parent[u][i];
        diff >>= 1;
    }
    if( u == v )
        return u;

    for(int i=MAX_SIZE-1; i>=0; i--){
        if( parent[u][i] != parent[v][i] ){
            u = parent[u][i];
            v = parent[v][i];
        }
    }
    return parent[u][0];
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    adj.resize(N+1), parent.resize(N+1, vector<int>((int)ceil(log2(N))+1) );
    depth.resize(N+1);

    for(int i=0; i<N-1; i++){
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    // root = 1
    setDepthAndFirstParent(1, 0, -1);
    setParent();

    cin >> M;
    for(int i=0; i<M; i++){
        int u, v;
        cin >> u >> v;
        cout << LCA(u, v) << '\n';
    }

    return 0;
}
