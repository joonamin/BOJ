#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, dp[10005][2],  val[10005];
vector<int> adj[10005];
int dfs(int prev, int current, int state) {
    int &ret = dp[current][state];
    if(ret != -1)
        return ret;

    if(state == 1) {
        ret = val[current];
        for(auto next : adj[current]) {
            if(next == prev)
                continue;
            ret += dfs(current, next, 0);
        }
    }
    else if(state == 0) {
        ret = 0;
        // 하위 서브트리에서 최소 state=1 인 것을 1개 이상 선택
        for(auto next : adj[current]) {
            if(next == prev)
                continue;
            ret += max(dfs(current, next, 0), dfs(current, next, 1));
        }
    }
    return ret;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    memset(dp, -1, sizeof(dp));
    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> val[i];
    }
    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    cout << max(dfs(-1, 1, 0), dfs(-1, 1, 1));


    return 0;
}
