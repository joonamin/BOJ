#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>

using namespace std;

typedef tuple<int, int, int> tp;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

int main(void) {
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    int board[130][130], N, T = 1;

    while (cin >> N, N > 0) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cin >> board[i][j];
            }
        }

        priority_queue<tp, vector<tp>, greater<>> PQ;
        vector<vector<int>> dist(N, vector<int>(N, 1e9));
        dist[0][0] = board[0][0];
        PQ.push({dist[0][0], 0, 0});
        while (!PQ.empty()) {
            auto[acDist, r, c] = PQ.top();
            PQ.pop();

            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0], nc = c + dir[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N
                    || acDist + board[nr][nc] >= dist[nr][nc]) {
                    continue;
                }
                dist[nr][nc] = acDist + board[nr][nc];
                PQ.push({dist[nr][nc], nr, nc});
            }
        }

        cout << "Problem " << T++ << ": " << dist[N - 1][N - 1] << '\n';
    }


    return 0;
};
