#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, board[105][105], ans = 1e9;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
bool check(int mn, int mx){
    queue<pair<int,int>> Q;
    if(board[1][1] < mn || board[1][1] > mx)
        return false;
    Q.push({1, 1});

    bool visited[105][105] = {false, };
    while(!Q.empty()){
        auto [y, x] = Q.front(); Q.pop();
        if(y == N && x == N && (mn <= board[y][x] && board[y][x] <= mx))
            return true;
        for(int d=0; d<4; d++){
            int ny = y + dir[d][0], nx = x + dir[d][1];
            if(ny <= 0 || nx <= 0 || ny > N || nx > N || visited[ny][nx])
                continue;
            if(board[ny][nx] < mn || board[ny][nx] > mx)
                continue;

            visited[ny][nx] = true;
            Q.push({ny, nx});
        }
    }
    return false;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N;
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> board[i][j];
        }
    }

    int l = 0, r = 200;
    while(l <= r){
        int m = (l + r) / 2;
        // 1. mx >= mn
        // 2. m = mx - mn
        // 2-1) mx := [0, 200], mn := [0, mx]
        bool isOk = false;
        for(int mx=0; mx<=200 && !isOk; mx++){
            int mn = mx - m;
            // mn 이상 mx 이하의 칸만 밟고 (n, n)에 도달가능한가?
            isOk = check(mn, mx);
        }
        if(isOk){
            ans = m;
            r = m - 1;
        }else{
            l = m + 1;
        }
    }
    cout << ans ;
    return 0;
}
