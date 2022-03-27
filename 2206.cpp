#include <iostream>
#include <vector>
#include <queue>
using namespace std;
class POINT{
public:
    int y,x, dist, block;
};
// vistied 배열을 3차원 으로 만들자.
// 벽돌을 깨고 방문한 특정 좌표와 벽돌을 깨지 않고 방문한 특정 좌표는 다르게 취급해야함
char board[1001][1001];
bool visited[2][1001][1001];
int N,M;
const int dir[4][2] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
int BFS(int sy, int sx){
    queue< POINT > Q;
    visited[0][0][0] = visited[1][0][0] = true;
    Q.push({0,0, 1,1});

    while(!Q.empty()){
        POINT np = Q.front(); Q.pop();
        if( np.y == N-1 && np.x == M-1 )
            return np.dist;

        for(int i=0; i<4; i++){
            int ny = np.y + dir[i][0];
            int nx = np.x + dir[i][1];
            if( ny < 0 || ny >= N || nx < 0 || nx >= M ) continue;
            if( visited[np.block][ny][nx] ) continue;

            // 깰 수 있는 블록 수가 없을 경우에는 1로 이동을 못한다.
            if( np.block == 0 && board[ny][nx] == '1' ) continue;

            if( np.block > 0 && board[ny][nx] == '1' ){
                visited[np.block][ny][nx] = true;
                Q.push({ny,nx, np.dist+1, np.block-1});
            }
            if( board[ny][nx] == '0' ){
                visited[np.block][ny][nx] = true;
                Q.push({ny,nx,np.dist+1,np.block});
            }
        }
    }

    return -1;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> board[i][j];
        }
    }

    int answer = BFS(0,0);
    cout << answer;
    return 0;
}