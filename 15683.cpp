#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1,0},{0,1},{1,0},{0,-1}};
int N, M, ans = 1e9;
int board[9][9], info[9][9] = {0,}; // info[y][x] : 현재 cctv가 바라보는 방향
vector<vector<int>> dBoard[9][9]; // [y][x] 가 가질 수 있는 방향들
vector<pair<int,int>> cctvPos;

void DFS(int idx){
    if( idx == cctvPos.size() ){
        // info[y][x] 정보를 통하여 BFS 수행
        int cpy_board[9][9];
        memcpy(cpy_board, board, sizeof(board));

        queue<tuple<int,int,int>> Q;
        // { y, x, 바라보는 방향 }
        for(auto pos : cctvPos){
            int y = pos.first, x = pos.second;
            for(auto d : dBoard[y][x][info[y][x]]){
                int ny = y + dir[d][0], nx = x + dir[d][1];
                if( ny < 0 || nx < 0 || ny >= N || nx >= M )
                    continue;
                if( board[ny][nx] == 6 )
                    continue;
                Q.push({y,x,d});
            }
        }
        while(!Q.empty()){
            auto fr = Q.front(); Q.pop();
            int y = get<0>(fr), x = get<1>(fr), d = get<2>(fr);
            int ny = y + dir[d][0], nx = x + dir[d][1];
            if( ny < 0 || nx < 0 || ny >= N || nx >= M || board[ny][nx] == 6)
                continue;
            board[ny][nx] = -1; // 감시하는 영역
            Q.push({ny,nx,d});
        }
        int ret = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if( board[i][j] == 0 )
                    ret += 1;
            }
        }
        ans = min(ans, ret);
        memcpy(board, cpy_board, sizeof(cpy_board));
        return;
    }
    // info를 업데이트
    int y = cctvPos[idx].first, x = cctvPos[idx].second;
    for(int i=0; i<dBoard[y][x].size(); i++){
        info[y][x] = i;
        DFS(idx+1);
    }
}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++) {
            cin >> board[i][j];
            if( 1 <= board[i][j] && board[i][j] <= 5 )
                cctvPos.push_back({i,j});
            if( board[i][j] == 1 )
                dBoard[i][j] = {{0}, {1}, {2}, {3}};
            else if( board[i][j] == 2 )
                dBoard[i][j] = {{0,2}, {1,3}};
            else if( board[i][j] == 3 )
                dBoard[i][j] = {{0,1}, {1,2}, {2,3}, {3,0}};
            else if( board[i][j] == 4 )
                dBoard[i][j] = {{1,2,3}, {0,2,3}, {0,1,3}, {0,1,2}};
            else if( board[i][j] == 5)
                dBoard[i][j] = {{0,1,2,3}};
        }
    }
    DFS(0);
    if( cctvPos.empty() ){
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if( board[i][j] == 0 )
                    cnt += 1;
            }
        }
        ans = cnt;
    }
    cout << ans ;
    return 0;
}
