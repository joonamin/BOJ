#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int W, H;
char board[105][105];
vector<pair<int,int>> pos;
const int dir[4][2] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
int dist[105][105];
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> W >> H;
    for(int i = 0; i<H; i++){
        for(int j=0; j<W; j++){
            cin >> board[i][j];
            if(board[i][j] == 'C')
                pos.emplace_back(i, j);
        }
    }

    for(int i=0; i<105; i++){
        for(int j=0; j<105; j++)
            dist[i][j] = 1e9;
    }
    typedef tuple<int, int, int, int> tp; // {cost, y, x, direction}
    priority_queue<tp, vector<tp>, greater<tp>> PQ;
    for(int i=0; i<4; i++)
        PQ.push({0, pos[0].first, pos[0].second, i});

    dist[pos[0].first][pos[0].second] = 0;
    while(!PQ.empty()){
        auto [mirror, y, x, d] = PQ.top(); PQ.pop();
        int nd[3] = {d, (d+1)%4, (d+3)%4};
        int nm[3] = {mirror, mirror+1, mirror+1};
        for(int i=0; i<3; i++){
            int ny = y + dir[nd[i]][0], nx = x + dir[nd[i]][1];
            if(ny < 0 || nx < 0 || ny >= H || nx >= W || board[ny][nx] == '*') continue;
            if(dist[ny][nx] < nm[i]) continue;
            dist[ny][nx] = nm[i];
            PQ.push({nm[i], ny, nx, nd[i]});
        }
    }
    cout << dist[pos[1].first][pos[1].second];
    return 0;
}
