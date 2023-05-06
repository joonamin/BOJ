#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int W, H, ans = 1e9;
char board[101][101];
vector<pair<int,int>> pos;
const int dir[4][2] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
bool checkBoundary(int r, int c) {
    return 0 <= r && r < H && 0 <= c && c < W;
}
int dijkstra(int sy, int sx, int d) {
    int dist[101][101][4];
    for (int i = 0; i < 101; i++) {
        for (int j = 0; j < 101; j++) {
            for (int k = 0; k < 4; k++) {
                dist[i][j][k] = 1e9;
            }
        }
    }
    // {count, r, c, d}
    priority_queue<tuple<int, int, int, int>, vector<tuple<int, int, int, int>>, greater<>> pq;
    dist[sy][sx][d] = 0;
    pq.push({0, sy, sx, d});

    while (!pq.empty()) {
        auto [cnt, r, c, d] = pq.top(); pq.pop();
        int nr, nc, nd;

        nd = d, nr = r + dir[nd][0], nc = c + dir[nd][1];
        if (checkBoundary(nr, nc) && dist[nr][nc][nd] > cnt && board[nr][nc] != '*') {
            dist[nr][nc][nd] = cnt;
            pq.push({cnt, nr, nc, nd});
        }

        // 오른쪽 방향 전환
        nd = (d + 1) % 4, nr = r + dir[nd][0], nc = c + dir[nd][1];
        if (checkBoundary(nr, nc) && dist[nr][nc][nd] > cnt + 1 && board[nr][nc] != '*') {
            dist[nr][nc][nd] = cnt + 1;
            pq.push({cnt + 1, nr, nc, nd});
        }

        // 왼쪽 방향 전환
        nd = (d + 3) % 4, nr = r + dir[nd][0], nc = c + dir[nd][1];
        if (checkBoundary(nr, nc) && dist[nr][nc][nd] > cnt + 1 && board[nr][nc] != '*') {
            dist[nr][nc][nd] = cnt + 1;
            pq.push({cnt + 1, nr, nc, nd});
        }
    }
    int res = dist[pos[1].first][pos[1].second][0];
    for (int i = 1; i < 4; i++) {
        res = min(res, dist[pos[1].first][pos[1].second][i]);
    }
    return res;
}

int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> W >> H;
    for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
            cin >> board[i][j];
            if (board[i][j] == 'C') {
                pos.emplace_back(i, j);
            }
        }
    }

    // 현재 위치 (r,c) / 현재 방향 / 현재까지 설치한 거울의 개수
    for (int i = 0; i < 4; i++) {
        int res = dijkstra(pos[0].first, pos[0].second, i);
        ans = min(ans, res);
    }
    cout << ans;
    return 0;
}
