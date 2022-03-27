#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
char board[105][105];
long long H, W, dist[3][105][105];
vector<pair<int,int>> pos;
typedef tuple<int,int,int> tp;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
void dijkstra(int nth, int sy, int sx){
    priority_queue<tp, vector<tp>, greater<tp>> PQ;
    dist[nth][sy][sx] = 0;
    PQ.push({0, sy, sx});

    while(!PQ.empty()){
        auto [acCost, y, x] = PQ.top(); PQ.pop();
        for(int i=0; i<4; i++){
            int ny = y + dir[i][0], nx = x + dir[i][1];
            if(ny < 0 || nx < 0 || ny > H+1 || nx > W+1 || board[y][x] == '*')
                continue;
            int added = (board[ny][nx] == '#') ? 1 : 0;
            if(dist[nth][ny][nx] <= acCost + added){
                continue;
            }
            dist[nth][ny][nx] = acCost + added;
            PQ.push({dist[nth][ny][nx], ny, nx});
        }
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int T;
    cin >> T;
    while(T--){
        long long ans = 1e9;
        cin >> H >> W;
        for(int i=0; i<105; i++){
            for(int j=0; j<105; j++)
                board[i][j] = '.';
        }
        pos.clear();
        pos.push_back({0, 0});
        for(int i=0; i<3; i++){
            for(int j=0; j<105; j++){
                for(int k=0; k<105; k++)
                    dist[i][j][k] = 1e9;
            }
        }

        for(int i=1; i<=H; i++){
            for(int j=1; j<=W; j++){
                cin >> board[i][j];
                if(board[i][j] == '$')
                    pos.push_back({i, j});
            }
        }
        for(int i=0; i<3; i++){
            dijkstra(i, pos[i].first, pos[i].second);
        }

        for(int y=0; y<=H+1; y++){
            for(int x=0; x<=W+1; x++){
                if(board[y][x] == '*') continue;
                long long ret = dist[0][y][x] + dist[1][y][x] + dist[2][y][x];
                if(board[y][x] == '#')
                    ret -= 2;
                ans = min(ans, ret);
            }
        }
        cout << ans << '\n';
    }

    return 0;
}
