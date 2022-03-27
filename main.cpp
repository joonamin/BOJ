#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, M, ans = 1e9;
pair<int, int> pos[4];
bool visited[105][105] = {false,};
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
void DFS(int i, int r, int c, int acDist) {
    if (c == pos[0].first && r == pos[1].second) {
        // 현재 상태에서 DFS2 수행, 도달 가능성 확인
        DFS(r, c, acDist);
        return;
    }
    for (int d = 0; d < 4; d++) {
        int nr = r + dir[d][0], nc = c + dir[d][1];
        if (nr < 0 || nc < 0 || nr > N || nc > M || visited[nr][nc])
            continue;
        visited[nr][nr] = true;
        DFS1(nr, nc, acDist + 1);
    }
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> M;
    for(int i=0; i<4; i++){
        cin >> pos[i].first >> pos[i].second;
    }
    DFS1(0, 0, 0);



    return 0;
}
