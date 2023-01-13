#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int M, N, h[501][501];
vector<pair<int,int>> waypoints;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
bool canMakeSpanningTree(const int D) {
    // D이하인 모든 Edge를 이어서 만든 그래프에서 일부 edge를 제거하는 것으로 spanning tree 완성 가능
    vector<vector<bool>> visited(M, vector<bool>(N, false));
    queue<pair<int,int>> Q;
    Q.push({waypoints[0].first, waypoints[0].second});
    visited[waypoints[0].first][waypoints[0].second] = true;

    while (!Q.empty()) {
        auto [y, x] = Q.front();
        Q.pop();
        for (int d = 0; d < 4; d++) {
            int ny = y + dir[d][0], nx = x + dir[d][1];
            if (ny < 0 || nx < 0 || ny >= M || nx >= N || visited[ny][nx] || abs(h[ny][nx] - h[y][x]) > D)
                continue;

            visited[ny][nx] = true;
            Q.push({ny, nx});
        }
    }

    for (auto [y, x] : waypoints) {
        if (!visited[y][x])
            return false;
    }
    return true;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> M >> N;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            cin >> h[i][j];
        }
    }

    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            int temp;
            cin >> temp;
            if (temp == 1)
                waypoints.emplace_back(i, j);
        }
    }

    ll left = 0L, right = 1e9, ans;
    while (left <= right) {
        ll mid = (left + right) / 2;
        if (canMakeSpanningTree(mid)) {
            ans = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    cout << ans;

    return 0;
}
