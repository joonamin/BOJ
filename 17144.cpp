#include <iostream>
#include <queue>
const int dir[4][2] = { {-1,0}, {1,0}, {0, -1}, {0, 1} };
int R,C;
int board[51][51];

using namespace std;

void spread(){
    int cpy_board[51][51] = {false, };
    queue< pair<int,int> > Q;


    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            if( board[i][j] > 0 ) {
                Q.push({i,j});
            }
        }
    }

    while(!Q.empty()){
        pair<int,int> fr = Q.front(); Q.pop();
        int y = fr.first, x = fr.second;

        int rc = board[y][x];
        for(int i=0; i<4; i++){
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            if( ny < 0 || nx < 0 || ny >= R || nx >= C ) continue;
            if( board[ny][nx] == -1 ) continue;

            cpy_board[ny][nx] += (rc/5);
            board[y][x] -= (rc/5);
        }
    }
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            if( board[i][j] != -1 )
                board[i][j] += cpy_board[i][j];
        }
    }
    return;
}
void test(){
    cout << "#################\n";
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            cout << board[i][j] <<' ';
        }
        cout << '\n';
    }
    cout << "##################\n";
}
void circulate(){
    pair<int,int> cleaner[2];
    int k = 0;
    for(int i=0; i<R; i++){
        if( board[i][0] == -1 )
            cleaner[k++] = {i,0};
    }

    int y, x;
        // 반시계방향 회전
    y = cleaner[0].first, x = cleaner[0].second;
    // 왼쪽 세로
    for(int i = y-2 ; i>=0; i-- ){
        board[i+1][0] = board[i][0];
    }
    // 위쪽 가로
    for(int i=0; i<C-1; i++){
        board[0][i] = board[0][i+1];
    }
    // 오른쪽 세로
    for(int i= 1; i <= y ; i++){
        board[i-1][C-1] = board[i][C-1];
    }
    // 아랫쪽 가로
    for(int i=C-2; i >= 1; i--){
        board[y][i+1] = board[y][i];
    }

    board[y][1] = 0;

        // 시계 방향 회전
    y = cleaner[1].first, x = cleaner[1].second;

    // 왼쪽 세로
    for(int i=y+2; i<= R-1; i++){
        board[i-1][0] = board[i][0];
    }
    // 아랫쪽 가로
    for(int i=1; i<= C-1; i++){
        board[R-1][i-1] = board[R-1][i];
    }
    // 오른쪽 세로
    for(int i=R-2; i>=y; i--){
        board[i+1][C-1] = board[i][C-1];
    }
    // 위쪽 가로
    for(int i=C-2; i>=0; i--){
        board[y][i+1] = board[y][i];
    }

    board[y][1] = 0;

    return;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> R >> C;
    int T;
    cin >> T;

    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            cin >> board[i][j] ;
        }
    }


    while(T--){
        spread();
        circulate();
    }

    int answer = 0;
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++)
            if( board[i][j] > 0 )
                answer += board[i][j];
    }
    cout << answer; 
    return 0;
}