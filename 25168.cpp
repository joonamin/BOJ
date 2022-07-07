#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, M, S, E, W;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N >> M;
    vector<pair<int, int>> adj[10005];
    int inDeg[10005] = {0,};

    for (int i = 0; i < M; i++) {
        cin >> S >> E >> W;
        adj[S].emplace_back(E, W);
        inDeg[E]++;
    }

    queue<int> Q;
    int dp[10005] = {0};
    for (int i = 1; i <= N; i++) {
        if (inDeg[i] == 0) {
            Q.push(i);
            dp[i] = 1;
        }
    }

    while (!Q.empty()) {
        int cur = Q.front();
        Q.pop();

        for (auto[next, cost]: adj[cur]) {
            int nt = cost + dp[cur];
            nt += (cost >= 7);
            dp[next] = max(dp[next], nt);
            inDeg[next]--;
            if (inDeg[next] == 0)
                Q.push(next);
        }
    }

    cout << *max_element(dp, dp + 10005);
    return 0;
}
