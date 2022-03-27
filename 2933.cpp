#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int R, C, area[105][105];
map<int, vector<pair<int,int>>> mp;
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
char board[105][105];
bool destroy(int row, int opt){
    int i = 0;
    if(opt == 0) {
        i = 0;
        while (i < C && board[row][i] == '.') i++;
        if (i == C)
            return false;
    }else{
        i = C-1;
        while(i >= 0 && board[row][i] == '.') i--;
        if(i == -1)
            return false;
    }
    board[row][i] = '.';
    return true;
}
void DFS(int r, int c, const int id){
    for(int d=0; d<4; d++){
        int nr = r + dir[d][0], nc = c + dir[d][1];
        if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
        if(area[nr][nc] != -1 || board[nr][nc] != 'x') continue;
        area[nr][nc] = id;
        mp[id].push_back({nr, nc});
        DFS(nr, nc, id);
    }
}
void adjust(){
    memset(area, -1, sizeof(area));
    mp.clear();
    int id = 0;
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            if(board[i][j] == 'x' && area[i][j] == -1){
                area[i][j] = id;
                mp[id].push_back({i, j});
                DFS(i, j, id);
                id += 1;
            }
        }
    }
    // 덩어리 상태의 block들을 y축 평행이동
    for(auto &p : mp){
        int mpID = p.first;
        vector<pair<int,int>> &v = p.second;
        sort(v.begin(), v.end(), greater<pair<int,int>>());
        int k = 0;
        bool flag = true;
        while(flag){
            k++;
            for(const auto& [y, x] : v) {
                if(y + k >= R) flag = false;
                if(area[y+k][x] != -1 && area[y+k][x] != area[y][x]) flag = false;
            }
        }
        k--;
        for(auto& [y,x] : v) {
            board[y][x] = '.', board[y+k][x] = 'x';
            area[y][x] = -1, area[y+k][x] = mpID;
            y += k;
        }
    }

}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> R >> C;
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++)
            cin >> board[i][j];
    }

    int n;
    cin >> n;
    vector<int> target(n);
    for(auto &item : target)
        cin >> item;

    for(int i=0; i<target.size(); i++){
        int &tar = target[i];
        bool isDestroyed = false;
        isDestroyed = destroy(R-tar, i % 2);
        if(isDestroyed)
            adjust();
    }

    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++)
            cout << board[i][j];
        cout << '\n';
    }

    return 0;
}
