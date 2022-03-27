/*
 * counter example
 * 6 7
    #######
    #..BR##
    #.#####
    #.#O###
    #....##
    #######
    path : {좌, 하, 좌, 하, 우, 하, 우, 상} -> 8번
 *  문제점 ?
 *  visited에서 문제가 발생한 것 같음.
 *
 */

#include <bits/stdc++.h>
using namespace std;
int N,M;
char board[11][11];
bool visited[4][2][11][11]; // 어느방향으로? 어떤 공으로? (y,x)를 방문했는가?
pair<int,int> hole, red, blue;
void move(pair<int,int>& r, pair<int,int>& b, int d){
    const int dir[4][2] ={{-1,0},{1,0},{0,-1},{0,1}};
    pair<int,int> tmp_r = r, tmp_b = b;
    while(true){
        bool hasChanged = false;
        int ry = tmp_r.first + dir[d][0], rx = tmp_r.second + dir[d][1];
        int by = tmp_b.first + dir[d][0], bx = tmp_b.second + dir[d][1];

        if( tmp_r.first != -1 || tmp_r.second != -1 ) {
            if (0 <= ry && ry < N && 0 <= rx && rx < M && board[ry][rx] != '#'  /*&& !visited[d][0][ry][rx]*/) {
                if( ry != tmp_b.first || rx != tmp_b.second ) {
                    visited[d][0][ry][rx] = true;
                    tmp_r = {ry, rx};
                    hasChanged = true;
                }
            }
        }
        if( tmp_b.first != -1 || tmp_b.second != -1 ) {
            if (0 <= by && by < N && 0 <= bx && bx < M && board[by][bx] != '#' /*&& !visited[d][1][by][bx]*/) {
                if( by != tmp_r.first || bx != tmp_r.second ) {
                    visited[d][1][by][bx] = true;
                    tmp_b = {by, bx};
                    hasChanged = true;
                }
            }
        }
        if( !hasChanged ) break;

        if( tmp_r.first == hole.first && tmp_r.second == hole.second )
            tmp_r = {-1,-1};
        if( tmp_b.first == hole.first && tmp_b.second == hole.second )
            tmp_b = {-1,-1};
    }
    r = tmp_r;
    b = tmp_b;
}
int main(void){
//    ios_base::sync_with_stdio(false);
//    cin.tie(0); cout.tie(0);

    cin >> N >> M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++) {
            cin >> board[i][j];
            if( board[i][j] == 'O' )
                hole = {i,j};
            else if( board[i][j] == 'R' )
                red = {i,j};
            else if( board[i][j] == 'B' )
                blue = {i,j};

            if( board[i][j] == 'R' || board[i][j] == 'B' )
                board[i][j] = '.';
        }
    }

    queue<tuple<pair<int,int>, pair<int,int>, int>> Q;
    Q.push({red, blue, 0});

    int answer = -1;
    while(!Q.empty()){
        auto front = Q.front(); Q.pop();
        pair<int,int> r,b;
        r = get<0>(front), b = get<1>(front);
        int cnt = get<2>(front);
        if( cnt > 10 ) continue;

        // 빨간 구슬만 구멍에 빠졌을 때
        if( r.first == -1 && r.second == -1 && (b.first != -1 || b.second != -1) ){
            answer = cnt ;
            break;
        }

        // 움직임
        for(int i=0; i<4; i++){
            pair<int,int> nr = r, nb = b;
            move(nr, nb, i); // 내부에서 visited 처리를 모두 마친다.
            if( nr != r || nb != b ){
                Q.push({nr,nb,cnt+1});
                // test
                cout << "\n----------------------\n";
                cout << "현재 카운트 수 : " << cnt + 1 << '\n';
                for(int i=0; i<N; i++){
                    for(int j=0; j<M; j++){
                        if( i == nr.first && j == nr.second ){
                            cout << "R ";
                        }
                        else if( i == nb.first && j == nb.second ){
                            cout << "B ";
                        }
                        else cout << board[i][j] << ' ';
                    }
                    cout << '\n';
                }
                cout << "\n----------------------\n";

                //
            }
        }
    }
    cout << answer ;
    return 0;
}
