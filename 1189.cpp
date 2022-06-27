#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
int R, C, K, ans = 0;
char board[6][6];
bool visited[6][6] = {false,};
void dfs(int r, int c, int k) {
    if(k == 0) {
        if(r == 0 && c == C-1)
            ans += 1;
        return;
    }
    for (int d = 0; d < 4; d++) {
        int nr = r + dir[d][0], nc = c + dir[d][1];
        if(nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc] || board[nr][nc] == 'T') {
            continue;
        }
        visited[nr][nc] = true;
        dfs(nr, nc, k-1);
        visited[nr][nc] = false;
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> R >> C >> K;
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            cin >> board[i][j];
        }
    }

    visited[R-1][0] = true;
    dfs(R-1, 0, K-1);
    cout << ans ;

    return 0;
}
