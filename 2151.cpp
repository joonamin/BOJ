#include <bits/stdc++.h>
using namespace std;
struct Elem{
    int y, x, d, cnt;
};
int N;
char house[51][51];
const int dir[4][2] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N ;
    pair<int ,int> pos;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++) {
            cin >> house[i][j];
            if( house[i][j] == '#' ){
                pos.first = i, pos.second = j;
            }
        }
    }

    int answer = 1e9;
    // !위치에 거울을 설치해본다.
    // {y, x, 현재 빛의 방향, 거울 설치 개수}
    queue<Elem> Q;
    bool visited[51][51][2501] = {false, };
    for(int i=0; i<4; i++) {
        Q.push({pos.first, pos.second, i, 0});
        visited[pos.first][pos.second][0] = true;
    }

    while(!Q.empty()){
        Elem fr = Q.front(); Q.pop();
        int y = fr.y, x = fr.x, d = fr.d, cnt = fr.cnt;

        // DEBUG
        // cout << "y : " << y << ", x : " << x << ", d : " << d << ", cnt : " << cnt << '\n';

        // 현재 위치가 시작점이 아니면서 #에 도달할 수 있는 경로가 존재한다? = 후보 해
        if( (y != pos.first || x != pos.second) && house[y][x] == '#' )
            answer = min(answer, cnt);

        if( house[y][x] == '*' )
            continue;
        else if( house[y][x] == '!' ){
            // 거울을 설치해본다.
            int nd1 = (d+1)%4;
            int nd2 = (d-1) >= 0 ? d-1 : 4+(d-1);
            int ny1 = y + dir[nd1][0], nx1 = x + dir[nd1][1];
            int ny2 = y + dir[nd2][0], nx2 = x + dir[nd2][1];
            if( 0 <= ny1 && ny1 < N && 0 <= nx1 && nx1 < N && !visited[ny1][nx1][cnt+1] ){
                visited[ny1][nx1][cnt+1] = true;
                Q.push({ny1, nx1, nd1, cnt+1});
            }
            if( 0 <= ny2 && ny2 < N && 0 <= nx2 && nx2 < N && !visited[ny2][nx2][cnt+1] ){
                visited[ny2][nx2][cnt+1] = true;
                Q.push({ny2, nx2, nd2, cnt+1});
            }
        }

        // 거울을 설치하지 않고 진행해본다.
        int ny = y + dir[d][0], nx = x + dir[d][1];
        if( ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx][cnt] )
            continue;
        Q.push({ny, nx, d, cnt});

    }

    cout << answer;
    return 0;
}