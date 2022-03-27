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
    int T;
    cin >> T;
    while(T--){
        int n, m, t;
        cin >> n >> m >> t;

        int s, g, h;
        cin >> s >> g >> h;

        vector<pair<int,int>> adj[2005];
        for(int i=0; i<m; i++){
            int a, b, d;
            cin >> a >> b >> d;
            if((a == g && b == h) || (a == h && b == g)){
                // g <-> h 사이의 간선만 홀수 cost
                d = 2 * d - 1;
            }else{
                d *= 2;
            }
            adj[a].push_back({d, b});
            adj[b].push_back({d, a});
        }

        vector<int> cand(t);
        for(auto &item : cand)
            cin >> item;

        // dijkstra
        typedef tuple<long long, int> tp;
        priority_queue<tp, vector<tp>, greater<tp>> PQ;
        PQ.push({0, s});

        vector<int> dist(n+1, 1e9);
        while(!PQ.empty()){
            auto [acCost, cur] = PQ.top(); PQ.pop();
            for(auto [cost, next] : adj[cur]){
                if(acCost + cost >= dist[next])
                    continue;
                dist[next] = acCost + cost;
                PQ.push({dist[next], next});
            }
        }
        sort(cand.begin(), cand.end());
        for(auto c : cand){
            if(dist[c] & 1)
                cout << c << ' ';
        }
        cout << '\n';
    }


    return 0;
}
