#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

char board[21][21];
bool visited[21][21][1<<10];
int N, M;
pair<int,int> cleaner;
vector<pair<int,int>> v;
map<pair<int,int>,int> mp;
int BFS(){
    // dist, y, x, bitmask
    const int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};
    typedef tuple<int, int, int, int> tp;
    queue<tp> Q;
    Q.push({0, cleaner.first, cleaner.second, 0});
    while(!Q.empty()){
        int y, x, dist, bitmask;
        tie(dist, y, x, bitmask) = Q.front(); Q.pop();
        if(bitmask == (1<<v.size()) - 1)
            return dist;

        for(int d=0; d<4; d++){
            int ny = y + dir[d][0], nx = x + dir[d][1];
            if(ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
            if(board[ny][nx] == 'x') continue;

            int newBitmask = bitmask;
            if(board[ny][nx] == '*'){
                newBitmask |= (1 << mp[{ny,nx}]);
            }
            if(visited[ny][nx][newBitmask]) continue;

            visited[ny][nx][newBitmask] = true;
            Q.push({dist+1, ny, nx, newBitmask});
        }
    }
    return -1;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    while(cin >> M >> N, N != 0 && M != 0){
        memset(visited, false, sizeof(visited));
        mp.clear(); v.clear();
        int idx = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++) {
                cin >> board[i][j];
                if(board[i][j] == 'o')
                    cleaner = {i, j};
                else if(board[i][j] == '*') {
                    v.push_back({i, j});
                    mp[{i,j}] = idx++;
                }
            }
        }

        int ans = BFS();
        cout << ans << '\n';
    }

    return 0;
}
