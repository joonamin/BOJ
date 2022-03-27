#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N, M;
ll ans;
bool visited[1001] = {false, };
vector<pair<int, int>> adj[1001];
void DFS(int u, const int target, ll acSum){
    if(u == target){
        ans = acSum;
        return;
    }
    for(auto [next, cost] : adj[u]){
        if(visited[next]) continue;
        visited[next] = true;
        DFS(next, target, acSum + cost);
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> M;
    // cycle이 발생하지 않는다는 전제 조건
    for(int i=0; i<N-1; i++){
        int u, v, cost;
        cin >> u >> v >> cost;
        adj[u].push_back({v, cost});
        adj[v].push_back({u, cost});
    }
    // DFS로 풀만함
    while(M--){
        ans = 0;
        memset(visited, false, sizeof(visited));
        int u, v;
        cin >> u >> v;
        visited[u] = true;
        DFS(u, v, 0);
        cout << ans << '\n';
    }
    return 0;
}
