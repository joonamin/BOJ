#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int R, C;
char board[10001][501];
const int dir[3][2] = {{-1, 1}, {0, 1}, {1, 1}};
bool visited[10001][501] = {false,};

bool dfs(int r, int c) {
    if (c == C-1)
        return true;
    for (int d = 0; d < 3; d++) {
        int nr = r + dir[d][0], nc = c + dir[d][1];
        if (nr < 0 || nc < 0 || nr >= R || nc >= C || board[nr][nc] == 'x' || visited[nr][nc]) {
            continue;
        }
        visited[nr][nc] = true;
        if (dfs(nr, nc))
            return true;
    }
    return false;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> R >> C;
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            cin >> board[i][j];
        }
    }
    int ans = 0;
    for (int i = 0; i < R; i++) {
        ans += dfs(i, 0);
    }
    cout << ans ;
    return 0;
}
