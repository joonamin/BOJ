#include <bits/stdc++.h>
using namespace std;
int N, M;
char board[1001][1001];
int areaNum[1001][1001] = {0, };

int counter = 0;
void DFS(int r, int c){
    // 같은 영역으로 표시, 대신 이번 스테이지에서 방문했던 곳이라면 다시 방문하지 않는다.
    int nr = r, nc = c;
    if( board[r][c] == 'U' )
        nr -= 1;
    else if( board[r][c] == 'D' )
        nr += 1;
    else if( board[r][c] == 'L' )
        nc -= 1;
    else if( board[r][c] == 'R' )
        nc += 1;

    if( nr < 0 || nc < 0 || nr >= N || nc >= M || areaNum[nr][nc] == counter )
        return;

    areaNum[nr][nc] = counter;
    DFS(nr, nc);
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M ;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cin >> board[i][j];
    }

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if( areaNum[i][j] == 0 ){
                areaNum[i][j] = counter;
                DFS(i, j);
                counter += 1;
            }
        }
    }
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cout << areaNum[i][j] << ' ';
        cout << '\n';
    }


    set<int> s;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            s.insert(areaNum[i][j]);
        }
    }
    cout << s.size();
    return 0;
}

/*
 * 반례
 * 4 4
DLRD
DLUL
DLUD
RRUU
 *
 */