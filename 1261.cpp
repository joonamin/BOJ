#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, M;
char board[105][105];
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> M >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> board[i][j];
        }
    }

    deque<tuple<int,int,int>> dq;
    int dist[105][105];
    fill(&dist[0][0], &dist[104][104], 1e9);
    dist[0][0] = 0;
    dq.push_back({0, 0, 0});

    while(!dq.empty()) {
        auto [br, r, c] = dq.front(); dq.pop_front();
        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if(nr < 0 || nc < 0 || nr >= N || nc >= M)
                continue;
            if(dist[nr][nc] > dist[r][c] + (board[nr][nc] == '1')) {
                dist[nr][nc] = dist[r][c] + (board[nr][nc] == '1');
                if (board[nr][nc] == '1') {
                    dq.push_back({br + 1, nr, nc});
                } else {
                    dq.push_front({br, nr, nc});
                }
            }
        }
    }
    cout << dist[N-1][M-1] << '\n';
    return 0;
}
