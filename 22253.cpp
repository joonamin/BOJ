#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
const int MOD = 1e9 + 7;
long long N, val[100005], dp[2][100005][15];
vector<int> adj[100005], tree[100005];
void makeTree(int prev, int cur){
    for(auto next : adj[cur]){
        if(next != prev){
            tree[cur].push_back(next);
            makeTree(cur, next);
        }
    }
}
int dfs(int opt, int j, int k){
    long long &ret = dp[opt][j][k];
    if(ret != -1)
        return ret;
    ret = 0;
    for(auto child : tree[j]){
        ret += dfs(0, child, k);
        ret %= MOD;
        if(val[child] >= k) {
            ret += dfs(1, child, val[child]);
            ret %= MOD;
        }
    }
    ret += opt;
    ret %= MOD;
    return ret;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N;

    for(int i=1; i<=N; i++)
        cin >> val[i];

    for(int i=0; i<N-1; i++){
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    makeTree(0, 1);
    memset(dp, -1, sizeof(dp));
    cout << (dfs(0, 1, 0) + dfs(1, 1, val[1])) % MOD;
    return 0;
}
