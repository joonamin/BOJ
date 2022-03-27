#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1,0},{0,1},{1,0},{0,-1}}; // 북, 동, 남, 서

int N,M;
int board[51][51];
bool visited[51][51];
int r,c,d;
int answer;

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> M ;
    cin >> r >> c >> d;

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cin >> board[i][j];
    }

    answer = 1, visited[r][c] = true;
    while(true){
        // 4방면을 탐색한다.
        bool canFind = false;
        for(int i=1; i<=4; i++) {
            int nd = (d - i) < 0 ? 4+d-i : d - i;
            int nr = r + dir[nd][0];
            int nc = c + dir[nd][1];

            if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && board[nr][nc] != 1) {
                canFind = true;
                d = nd;
                r = nr;
                c = nc;
                break;
            }

        }
        // 현재 칸을 청소한다.
        if(canFind){
            visited[r][c] = true;
            answer += 1;
        }
        // 4방향 모두 움직일 수 없는 경우 후진을 한다.
        else{
            int back_idx = (d + 2)%4;
            int nr = r + dir[back_idx][0];
            int nc = c + dir[back_idx][1];

            // 후진을 할 수 없는 경우
            if( nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 1 )
                break;

            r = nr ;
            c = nc ;

        }

    }

    cout << answer;

    return 0;
}

