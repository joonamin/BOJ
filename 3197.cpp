#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
int R, C, area[1505*1505], rnk[1505*1505] = {0, }; // 1505 * 1505
char board[1505][1505];

queue<pair<int,int>> Q;
vector<pair<int,int>> pos;
int find(int u){
    if(area[u] == u)
        return u;
    return area[u] = find(area[u]);
}
void _union(int u, int v){
    u = find(u), v = find(v);
    if(u == v)
        return;
    if(rnk[u] > rnk[v])
        swap(u, v);
    area[u] = v;
    if(rnk[u] == rnk[v])
        rnk[v]++;
}
void BFS(){
    queue<pair<int,int>> nextQ;
    bool visited[1505][1505] = {false, };
    while(!Q.empty()){
        int y, x;
        tie(y, x) = Q.front(); Q.pop();
        for(int d=0; d<4; d++){
            int ny = y + dir[d][0], nx = x + dir[d][1];
            if(ny < 0 || nx < 0 || ny >= R || nx >= C || find(y*C + x) == find(ny*C + nx))
                continue;
            if(visited[ny][nx])
                continue;
            visited[ny][nx] = true;
            _union(y*C + x, ny*C + nx);
            nextQ.push({ny, nx});
            board[ny][nx] = '.';
        }
    }
    Q = nextQ;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> R >> C;
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            cin >> board[i][j];
            if(board[i][j] == 'L'){
                pos.push_back({i, j});
            }
            area[i*C + j] = i*C + j;
            if(board[i][j] == '.' || board[i][j] == 'L')
                Q.push({i, j});
        }
    }

    int ans = 0;
    while(find(pos[0].first * C + pos[0].second) !=
    find(pos[1].first * C + pos[1].second)){
        BFS();
        ans += 1;
    }

    cout << ans - 1;

    return 0;
}

// 매번 BFS마다 1500 x 1500 의 iteration 시, 시간초과..
// 최대 반복 횟수 t : 1500
