#include <bits/stdc++.h>
using namespace std;
int board[20][20], visited[20][20][4];
const int dir[4][2] = {{1,0},{0,1},{1,1},{1, -1}};
bool DFS(int y, int x, int d, int cnt){
    int ny = y + dir[d][0], nx = x + dir[d][1];
    if( cnt == 5 ){
        // 같은 방향으로 6목이 아닌지 추가로 검사한다.
        if( ny <= 0 || nx <= 0 || ny > 19 || nx > 19 || board[ny][nx] != board[y][x] )
            return true;
        while( 0 < ny && ny <= 19 && 0 < nx && nx <= 19 && board[ny][nx] == board[y][x] ){
            visited[ny][nx][d] = true;
            ny += dir[d][0], nx += dir[d][1];
        }
        return false;
    }
    if( ny <= 0 || nx <= 0 || ny > 19 || nx > 19 || visited[ny][nx][d] || board[ny][nx] != board[y][x] )
        return false;
    visited[ny][nx][d] = true;
    return DFS(ny, nx, d, cnt+1);
}
int main(){
    cin.tie(0) ->sync_with_stdio(false);

    for(int i=1; i<=19; i++){
        for(int j=1; j<=19; j++)
            cin >> board[i][j];
    }

    for(int i=1; i<=19; i++){

        for(int j=1; j<=19; j++){
            if( board[i][j] == 0 ) continue;
            for(int d=0; d<4; d++){
                if(visited[i][j][d]) continue;
                visited[i][j][d] = true;
                if( DFS(i, j, d, 1) ){
                    cout << board[i][j] << '\n';
                    if( d == 3 )
                        cout << i+4 << ' ' << j-4;
                    else
                        cout << i << ' ' << j;
                    exit(0);
                }
            }
        }
    }
    cout << 0;

    return 0;
}