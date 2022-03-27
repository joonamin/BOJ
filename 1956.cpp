#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int V, E, dist[405][405];
vector<vector<pair<int,int>>> adj;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> V >> E;
    adj.resize(V+1);

    for(int i=0; i<405; i++){
        for(int j=0; j<405; j++) {
            dist[i][j] = 2e9;
        }
    }

    for(int i=0; i<E; i++){
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].push_back({c, b});
        dist[a][b] = c;
    }

    for(int k=1; k<=V; k++){
        for(int i=1; i<=V; i++){
            for(int j=1; j<=V; j++){
                if(dist[i][k] != 2e9 && dist[k][j] != 2e9){
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    int ans = 2e9;
    for(int st = 1; st <= V; st++){
        for(int dst = 1; dst <= V; dst++){
            if(dist[st][dst] != 2e9 && dist[dst][st] != 2e9){
                ans = min(ans, dist[st][dst] + dist[dst][st]);
            }
        }
    }
    if(ans != 2e9)
        cout << ans ;
    else
        cout << -1;
    return 0;
}
