#include <bits/stdc++.h>
using namespace std;
int id[1001][1001] = {0, }, N, M, counter = 1;
char board[1001][1001];
int DFS(int r, int c){
    if( id[r][c] != 0 )
        return id[r][c];
    id[r][c] = counter ;
    int nr = r, nc = c;
    switch(board[r][c]){
        case 'U':
            nr -= 1;
            break;
        case 'D':
            nr += 1;
            break;
        case 'L':
            nc -= 1;
            break;
        case 'R':
            nc += 1;
            break;
    }
    // 밖으로 나가는 경우는 없다.
    if( 0 <= nr && nr < N && 0 <= nc && nc < M ){
        return id[r][c] = DFS(nr,nc);
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr), cout.tie(nullptr);

    cin >> N >> M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            cin >> board[i][j];
    }

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if( id[i][j] == 0 ) {
                DFS(i, j);
                counter += 1;
            }
        }
    }

    set<int> s;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++)
            s.insert(id[i][j]);
    }
    cout << s.size();
    return 0;
}