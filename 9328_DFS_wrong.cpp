#include <bits/stdc++.h>
using namespace std;
int h,w;
int answer = 0;
const int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};

char board[105][105];
bool visited[105][105];

string key;

void DFS(int y, int x){
//    // test
//    cout << "current (y,x) : " << "( " << y << ", " << x << " )\n ";

    bool mask[26] = {false, };
    for(auto c : key) {
        if( c != '0' )
            mask[c - 'a'] = true;
    }

    for(int i=0; i<4; i++){
        int ny = y + dir[i][0], nx = x + dir[i][1];

        if( ny < 0 || nx < 0 || ny >= h+2 || nx >= w+2 || visited[ny][nx] )
            continue;
        if( board[ny][nx] == '*')
            continue;

        // 문을 마주했을 때
        if( 'A' <= board[ny][nx] && board[ny][nx] <= 'Z' ){
            // 문을 마주했고, 키가 없었을 때는 못 지나감
            if( !mask[board[ny][nx] - 'A'] ) continue;
        }
        else if( 'a' <= board[ny][nx] && board[ny][nx] <= 'z' ){
            if( !mask[board[ny][nx]-'a'] ) {
                key += board[ny][nx];
                // 키를 획득했을 때는 왔던 곳을 다시 방문 할 수 있음.
                memset(visited, false, sizeof(visited));
            }
        }
        else if( board[ny][nx] == '$' ){
            answer += 1;
            board[ny][nx] = '.' ; // 이미 먹음
        }
        visited[ny][nx] = true;
        DFS(ny,nx);
    }

}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int T;
    cin >> T;

    while(T--){
        answer = 0;
        memset(visited, false, sizeof(visited));

        cin >> h >> w;

        ///// 테두리를 움직일 수 있는 공간으로 바꿈 /////
        for(int i=0; i<w+2; i++){
            board[0][i] = '.';
            board[h+1][i] = '.';
        }
        for(int i=0; i<h+2; i++){
            board[i][0] = '.';
            board[w+1][i] = '.';
        }
        /////////////////////////////////////////
        for(int i=1; i<=h; i++){
            for(int j=1; j<=w; j++){
                cin >> board[i][j];
            }
        }

        cin >> key;
        // 다시 뒤돌아 갈 수 있는 경우는 오직 열쇠를 새로 획득했을 때
        DFS(0,0);
        cout << answer << '\n';
    }

    return 0;
}