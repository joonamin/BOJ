#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
int N, M, board[102][102];
int BFS(){
    queue<pair<int,int>> Q;
    set<pair<int,int>> v;
    Q.push({0,0});
    bool visited[102][102] = {true, };
    while(!Q.empty()){
        int y, x;
        tie(y, x) = Q.front(); Q.pop();
        for(int i=0; i<4; i++){
            int ny = y + dir[i][0], nx = x + dir[i][1];
            if(ny < 0 || nx < 0 || ny > N || nx > M || visited[ny][nx]) continue;
            if(board[ny][nx] == 1){
                v.insert({ny, nx});
                continue;
            }
            visited[ny][nx] = true;
            Q.push({ny, nx});
        }
    }

    for(const auto& pos : v)
        board[pos.first][pos.second] = 0;
    return (int)v.size();
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> M;
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            cin >> board[i][j];
        }
    }

    int ans, temp, t=0;
    while(true){
        temp = BFS();
        if(temp == 0) break;
        t++;
        ans = temp;
    }
    cout << t << '\n';
    cout << ans ;

    return 0;
}
