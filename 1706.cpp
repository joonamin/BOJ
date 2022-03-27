#define W 0
#define H 1
#include <bits/stdc++.h>
using namespace std;
int N,M;
string board[21];
const int dir[2][2] = {{0,1},{1,0}};
bool visited[21][21];
vector<string> answer;
void DFS(int y, int x, string str, int dirIdx){
    int ny = y + dir[dirIdx][0];
    int nx = x + dir[dirIdx][1];
    if( ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || board[ny][nx] == '#' ){
        if( str.length() >= 2 )
            answer.push_back(str);
        return ;
    }
    visited[ny][nx] = true;
    DFS(ny,nx, str + board[ny][nx], dirIdx);

}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M ;
    for(int i=0; i<N; i++){
        cin >> board[i];
    }

    // 가로 DFS
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++) {
            string str;
            str.push_back(board[i][j]);
            if (!visited[i][j] && board[i][j] != '#')
                DFS(i, j, str, W);
        }
    }

    // 세로 DFS
    memset(visited, false, sizeof(visited));
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            string str;
            str.push_back(board[i][j]);
            if(!visited[i][j] && board[i][j] != '#')
                DFS(i,j,str,H);
        }
    }
    sort(answer.begin(), answer.end());
    cout << answer[0];
    return 0;
}