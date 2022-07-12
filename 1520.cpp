#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int M, N, board[501][501], dp[501][501];
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
int dfs(int r, int c) {
    int &ret = dp[r][c];
    if (ret != -1) {
        return ret;
    }
    if (r == M-1 && c == N - 1)
        return 1;
    ret = 0;
    for (int d = 0; d < 4; d++) {
        int nr = r + dir[d][0], nc = c + dir[d][1];
        if (nr < 0 || nc < 0 || nr >= M || nc >= N || board[r][c] <= board[nr][nc])
            continue;
        ret += dfs(nr, nc);
    }
    return ret;
}

int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    // dfs(i, j) := (i, j)부터 (M-1, N-1) 까지 도달할 수 있는 모든 경우의 수
    cin >> M >> N;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            cin >> board[i][j];
        }
    }

    memset(dp, -1, sizeof(dp));
    cout << dfs(0, 0);

    return 0;
}
