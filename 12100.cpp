#include <bits/stdc++.h>
using namespace std;
int N;
int board[21][21];
int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}}; // 상, 하, 좌, 우

int answer;

void move_blocks(int d){
    // 이미 합쳐진 블록을 구분하는 배열이 필요
    bool merged[21][21] = {false, };

    // d 가 왼쪽일 때는 좌측 열부터 시작, d가 오른쪽일 때는 우측 열 부터 시작
    // d 가 위쪽일 때는 윗쪽 행부터 시작, d가 아래쪽일 때는 아래쪽 행 부터 시작
    int start_col = 0 , start_row = 0;
    int di = 1, dj = 1;
    if( d == 1 ) {
        di = -1;
        start_row = N-1;
    }
    if( d == 3 ){
        dj = -1;
        start_col = N-1;
    }


    int dy = dir[d][0], dx = dir[d][1];

    for(int i=start_row; 0 <= i && i < N ; i += di){
        for(int j=start_col; 0 <= j && j < N ; j += dj){
            // pos은 진행방향에서 가장 가까운 블록의 좌표가 된다.
            pair<int, int> pos = {i+dy,j+dx};
            while( 0 <= pos.first && pos.first < N &&
                   0 <= pos.second && pos.second < N && board[pos.first][pos.second] == 0){
                pos.first += dy;
                pos.second += dx;
            }

            if( 0 <= pos.first && pos.first < N && 0 <= pos.second && pos.second < N &&
                board[pos.first][pos.second] == board[i][j] &&
                !merged[pos.first][pos.second] ){
                merged[pos.first][pos.second] = true;
                board[pos.first][pos.second] *= 2 ;
                board[i][j] = 0;
            }
            else{
                // pos 이전의 위치에 삽입 ( 진행방향의 반대 방향 )
                int ny = pos.first - dy;
                int nx = pos.second - dx;
                board[ny][nx] = board[i][j] ;
                if( ny != i || nx != j )
                    board[i][j] = 0;
            }
        }
    }
}
void solve(int depth){
    if( depth == 0 ){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++) {
                answer = max(answer, board[i][j]);
            }
        }
        return ;
    }

    for(int i=0; i<4; i++){
        int cpy_board[21][21];
        memcpy( cpy_board, board, sizeof(board));
        move_blocks(i);
        solve(depth-1);
        memcpy( board, cpy_board, sizeof(cpy_board));
    }

}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            cin >> board[i][j];
    }

    solve(5);
    cout << answer;

//// test
//    move_blocks(3);
//    for(int i=0; i<N; i++){
//        for(int j=0; j<N; j++)
//            cout << board[i][j] << ' ';
//        cout << '\n';
//    }
    return 0;
}