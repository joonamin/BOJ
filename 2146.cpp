#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, board[101][101], area[101][101], counter = 1;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
map<int, vector<pair<int,int>>> mp;
void dfs(int r, int c) {
    for (int d = 0; d < 4; d++) {
        int nr = r + dir[d][0], nc = c + dir[d][1];
        if (nr < 0 || nc < 0 || nr >= N || nc >= N || area[nr][nc] || !board[nr][nc])
            continue;
        area[nr][nc] = counter;
        mp[counter].push_back({nr, nc});
        dfs(nr ,nc);
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> board[i][j];
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (area[i][j] == 0 && board[i][j]) {
                dfs(i, j);
                area[i][j] = counter;
                mp[counter++].push_back({i, j});
            }
        }
    }

    int dist = 1e9;
    for (int i = 1; i < counter; i++) {
        vector<pair<int,int>> c1 = mp[i];
        for (int j = i + 1; j < counter; j++) {
            vector<pair<int,int>> c2 = mp[j];
            for (auto [a, b] : c1) {
                for (auto [c, d] : c2) {
                    dist = min(dist, abs(a - c) + abs(b - d) - 1);
                }
            }
        }
    }

    cout << dist;
    return 0;
}
