#include <bits/stdc++.h>
using namespace std;
int N, M;
char board[21][21];
const int dir[4][2] = {{-1,0},{1,0},{0,-1},{0,1}};
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M;

    vector<pair<int,int>> ball;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++) {
            cin >> board[i][j];
            if( board[i][j] == 'o' )
                ball.push_back({i,j});
        }
    }

    // {ball1_pos, ball2_pos, cnt}
    int answer = -1;

    queue<tuple<pair<int,int>, pair<int,int>, int>> Q;
    Q.push({{ball[0].first, ball[0].second},{ball[1].first, ball[1].second}, 0});
    bool visited[21][21][21][21] = {false,};
    visited[ball[0].first][ball[0].second][ball[1].first][ball[1].second] = true;
    while(!Q.empty()){
        auto front = Q.front(); Q.pop();
        pair<int,int> b1 = get<0>(front), b2 = get<1>(front);
        int cnt = get<2>(front);

        if( cnt > 10 )
            continue;

        for(int i=0; i<4; i++){
            bool is_out[2] = {false, };
            // 가려고자 하는 방향에 #(벽)이 있으면 움직이지 않는다.
            pair<int,int> new_b1 = {b1.first + dir[i][0], b1.second + dir[i][1]};
            pair<int,int> new_b2 = {b2.first + dir[i][0], b2.second + dir[i][1]};

            if( 0 <= new_b1.first && new_b1.first < N && 0 <= new_b1.second && new_b1.second < M ){
                if(board[new_b1.first][new_b1.second] == '#') {
                    new_b1.first -= dir[i][0];
                    new_b1.second -= dir[i][1];
                }
            }
            if( 0 <= new_b2.first && new_b2.first < N && 0 <= new_b2.second && new_b2.second < M ){
                if(board[new_b2.first][new_b2.second] == '#') {
                    new_b2.first -= dir[i][0];
                    new_b2.second -= dir[i][1];
                }
            }

            if( 0 <= new_b1.first && new_b1.first < N && 0 <= new_b1.second && new_b1.second < M ){
                if( 0 <= new_b2.first && new_b2.first < N && 0 <= new_b2.second && new_b2.second < M){
                    if( visited[new_b1.first][new_b1.second][new_b2.first][new_b2.second] ){
                        continue;
                    }
                }
            }

            if( new_b1.first < 0 || new_b1.first >= N || new_b1.second < 0 || new_b1.second >= M )
                is_out[0] = true;
            if( new_b2.first < 0 || new_b2.first >= N || new_b2.second < 0 || new_b2.second >= M )
                is_out[1] = true;
            if( is_out[0] != is_out[1] && cnt + 1 <= 10 ){
                answer = cnt + 1;
                cout << answer;
                exit(0);
            }
            // 둘 다 나가거나, 둘 다 움직일 수 있거나
            if( !is_out[0] && !is_out[1] ){
                visited[new_b1.first][new_b1.second][new_b2.first][new_b2.second] = true;
                Q.push({{new_b1.first,new_b1.second},{new_b2.first,new_b2.second}, cnt+1});
            }
        }
    }

    cout << answer;

    return 0;
}
