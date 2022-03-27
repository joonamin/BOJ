#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef tuple<ll, int, int ,int> tp;
ll board[101][101], dist[3][101][101];
const int dir[3][4][2] = {{{-1,0},{1,0},{0,-1},{0,1}},{{-1,0},{1,0}}, {{0,-1},{0,1}} }, d[3] = {4,2,2};
int main(void){
    int N, M, sy, sx, dy, dx;
    cin >> N >> M >> sy >> sx >> dy >> dx;
    sy--, sx--, dy--, dx--;

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cin >> board[i][j];
    }

    for(auto& ddd: dist){
        for(auto& dd: ddd){
            for(auto& d: dd)
                d = 1e9;
        }
    }

    // {prefix, y, x, turn}
    priority_queue<tp, vector<tp>, greater<>> PQ;
    dist[1][sy][sx] = 0;
    PQ.push({0, sy, sx, 1});
    while(!PQ.empty()){
        auto top = PQ.top(); PQ.pop();
        ll prefixSum = get<0>(top), y = get<1>(top), x = get<2>(top), t = get<3>(top);
        if( dist[t][y][x] < prefixSum )
            continue;
        for(int i=0; i<d[t]; i++){
            int ny = y + dir[t][i][0], nx = x + dir[t][i][1];
            if( ny < 0 || nx < 0 || ny >= N || nx >= M || board[ny][nx] == -1 ) continue;
            if( dist[(t+1)%3][ny][nx] > prefixSum + board[ny][nx] ){
                dist[(t+1)%3][ny][nx] = prefixSum + board[ny][nx];
                PQ.push({dist[(t+1)%3][ny][nx], ny, nx, (t+1)%3});
            }
        }
    }

    ll ans = min( min(dist[0][dy][dx], dist[1][dy][dx]), dist[2][dy][dx] );
    if( ans >= 1e9 )
        cout << -1;
    else
        cout << ans ;
    return 0;
}