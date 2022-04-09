#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, M, K;
const int dir[][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
char board[1005][1005];
bool visited[2][11][1005][1005] = {false, };
typedef tuple<int, int, int, int, int> tp;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> M >> K;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> board[i][j];
        }
    }

    // 낮에만, 벽을 부술 수 있음. 이동하지 않고 같은 칸에 머무르는 경우도 존재.
    // {낮/밤, 부순 문의 수, 누적 거리, r, c}
    // state := [낮/밤][부순 문의 수][r][c] = 2 * 10 * 1000 * 1000
    // state_value := 이 상태에 도달하기 위해 누적거리의 최솟 값

    enum{DAYTIME=0, NIGHT = 1};
    queue<tp> Q;
    Q.push({DAYTIME, 0, 0, 0, 1});
    visited[DAYTIME][0][0][0] = true;

    int ans = 1e9;
    while(!Q.empty()) {
        auto [D, broke, r, c, acDist] = Q.front(); Q.pop();
        if(r == N-1 && c == M-1) {
            ans = min(ans, acDist);
            continue;
        }
        for (int i = 0; i < 4; i++) {
            int nr = r + dir[i][0], nc = c + dir[i][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                continue;
            if(board[nr][nc] == '1') {
                if(D == DAYTIME && broke + 1 <= K) {
                    if (!visited[(D + 1) % 2][broke + 1][nr][nc]) {
                        visited[(D + 1) % 2][broke + 1][nr][nc] = true;
                        Q.push({(D + 1) % 2, broke + 1, nr, nc, acDist + 1});
                    }
                } else if (D == NIGHT) {
                    if (!visited[(D + 1) % 2][broke][r][c]) {
                        visited[(D + 1) % 2][broke][r][c] = true;
                        Q.push({(D + 1) % 2, broke, r, c, acDist + 1});
                    }
                }
            } else if (board[nr][nc] == '0') {
                if (!visited[(D + 1) % 2][broke][nr][nc]) {
                    visited[(D + 1) % 2][broke][nr][nc] = true;
                    Q.push({(D + 1) % 2, broke, nr, nc, acDist + 1});
                }
            }
        }
    }
    if(ans == 1e9)
        cout << -1 << '\n';
    else
        cout << ans << '\n';
    return 0;
}
