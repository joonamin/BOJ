#include <bits/stdc++.h>
using namespace std;
int N, M, K;
bool condo[101][101];

int BFS(int y, int x){
    const int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};
    int ret = 1;

    queue<pair<int,int>> Q;
    condo[y][x] = false; // 방문표시
    Q.push({y,x});
    while(!Q.empty()){
        int yy = Q.front().first, xx = Q.front().second;
        Q.pop();

        for(int i=0; i<4; i++){
            int ny = yy + dir[i][0], nx = xx + dir[i][1];
            if( ny <= 0 || nx <= 0 || ny > N || nx > M || !condo[ny][nx] )
                continue;
            condo[ny][nx] = false;
            ret += 1;
            Q.push({ny,nx});
        }
    }

    return ret;
}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M >> K ;

    for(int i=0; i<K; i++){
        int r, c;
        cin >> r >> c;
        condo[r][c] = true;
    }

    int answer = 0 ;
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            if( condo[i][j] ){
                int size = BFS(i,j);
                if( size > answer )
                    answer = size;
            }
        }
    }
    cout << answer;
    return 0;
}
