#include <bits/stdc++.h>
using namespace std;
int N,M;
int farm[101][71];
bool visited[101][71] = {false, };
bool isPeak = true;
const int dir[8][2] = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
};
void DFS(int y, int x){
    for(int i=0; i<8; i++){
        int ny = y + dir[i][0], nx = x + dir[i][1];
        if( ny < 0 || ny >= N || nx < 0 || nx >= M ) continue;

        // 인접 칸에 더 높은 봉우리가 있는가?
        if( farm[y][x] < farm[ny][nx] ) isPeak = false;

        // 해당하는 산봉우리들을 탐색
        if( visited[ny][nx] || farm[y][x] != farm[ny][nx] ) continue;
        visited[ny][nx] = true;
        DFS(ny,nx);
    }
    return ;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> farm[i][j];
        }
    }

    int answer = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if( !visited[i][j] ){
                isPeak = true;
                DFS(i, j);
                if( isPeak ) answer += 1;
            }
        }
    }
    cout << answer ;
    return 0;
}