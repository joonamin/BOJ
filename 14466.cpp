#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
int N, K, R, ans = 0, adj[4][105][105];
vector<pair<int,int>> cow;
void dijkstra(int sy, int sx){
    typedef tuple<int,int,int> tp;
    priority_queue<tp, vector<tp>, greater<tp>> PQ;
    PQ.push({0, sy, sx});


    vector<vector<int>> dist(N+1, vector<int>(N+1, 1e9));
    dist[sy][sx] = 0;
    while(!PQ.empty()){
        int acSum, y, x;
        tie(acSum, y, x) = PQ.top(); PQ.pop();

        for(int d=0; d<4; d++){
            int ny = y + dir[d][0], nx = x + dir[d][1];
            if(ny <= 0 || nx <= 0 || ny > N || nx > N || acSum >= dist[ny][nx])
                continue;

            int &cost = adj[d][y][x];
            if(dist[ny][nx] > dist[y][x] + cost){
                dist[ny][nx] = dist[y][x] + cost;
                PQ.push({dist[y][x] + cost, ny, nx});
            }
        }
    }
    for(auto [cy, cx] : cow){
        if(dist[cy][cx] != 1e9 && dist[cy][cx] > 0)
            ans += 1;
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> K >> R;

    for(int i=0; i<R; i++){
        int a, b, c, d;
        cin >> a >> b >> c >> d;
        for(int k=0; k<4; k++){
            if(a + dir[k][0] == c && b + dir[k][1] == d)
                adj[k][a][b] = 1;
            if(c + dir[k][0] == a && d + dir[k][1] == b)
                adj[k][c][d] = 1;
        }
    }

    for(int i=0; i<K; i++){
        int r, c;
        cin >> r >> c;
        cow.emplace_back(r, c);
    }

    for(int i=0; i<cow.size(); i++){
        dijkstra(cow[i].first, cow[i].second);
    }

    cout << ans / 2;
    return 0;
}
