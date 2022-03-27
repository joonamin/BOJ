#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int n, a, b, m, ans = -1;
    vector<int> adj[105];
    cin >> n >> a >> b >> m;
    for(int i=0; i<m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    queue<pair<int,int>> Q;
    Q.push({a, 0});
    bool visited[105][105] = {false, };
    visited[a][a] = true;
    while(!Q.empty()){
        int cur, depth;
        tie(cur, depth) = Q.front(); Q.pop();
        if(cur == b){
            ans = depth;
            break;
        }
        for(const auto &next : adj[cur]){
            if(visited[cur][next]) continue;
            visited[cur][next] = true;
            Q.push({next, depth+1});
        }
    }
    cout << ans << '\n';
    return 0;
}
