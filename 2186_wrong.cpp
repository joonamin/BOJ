#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, M, K, dp[105][105][85];
char board[105][105];
string s;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
int dfs(int y, int x, int k){
    if(k == s.size() - 1) {
        dp[y][x][k] = 1;
        return dp[y][x][k];
    }

    int &ret = dp[y][x][k];
    if(ret != -1)
        return ret;
    ret = 0;
    // 최대 K칸 만큼 이동 가능
    vector<pair<int,int>> next;
    for(int d=0; d<4; d++){
        for(int l=1; l<=K; l++){
            int ny = y + l * dir[d][0], nx = x + l * dir[d][1];
            if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
            next.emplace_back(ny, nx);
        }
    }
    for(const auto &[ny, nx] : next){
        if(k+1 < s.size() && board[ny][nx] == s[k+1])
            ret += dfs(ny, nx, k+1);
    }
    return ret;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> M >> K;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> board[i][j];
        }
    }
    cin >> s;
    // dp[i][j][k] : [i][j] 까지 문자열의 k번째까지 매칭 되는 모든 경우의 수
    memset(dp, -1, sizeof(dp));
    int ans = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(board[i][j] == s[0])
                ans += dfs(i, j, 0);
        }
    }

    cout << ans ;
    return 0;
}
