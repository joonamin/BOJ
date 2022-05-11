#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int T, n, d, c, timer[10005] = {0, };;
vector<pair<int,int>> adj[10005];
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> T;
    while(T--) {
        cin >> n >> d >> c;
        for(auto& item : adj)
            item.clear();
        fill(timer, timer+10005, 1e9 + 10);

        for (int i = 0; i < d; i++) {
            int a, b, s;
            cin >> a >> b >> s;
            adj[b].push_back({s, a});
        }

        // {time, current}
        priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> PQ;
        PQ.push({0, c});
        timer[c] = 0;

        while(!PQ.empty()) {
            auto [t, cur] = PQ.top(); PQ.pop();
            for(auto [cost, next] : adj[cur]) {
                if(timer[next] <= cost + timer[cur])
                    continue;
                timer[next] = cost + timer[cur];
                PQ.push({timer[next], next});
            }
        }

        int cnt = 0, lastTime = 0;
        for (int i = 1; i <= n; i++) {
            if(timer[i] != 1e9 + 10){
                cnt += 1;
                lastTime = max(lastTime, timer[i]);
            }
        }
        cout << cnt << ' ' << lastTime << '\n';
    }

    return 0;
}
