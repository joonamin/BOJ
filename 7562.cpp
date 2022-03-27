#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int I;
pair<int,int> cur, target;
const int dir[8][2] ={
        {-1, -2}, {-1, 2}, {-2, -1}, {-2, 1},
        {1, -2}, {1, 2}, {2, -1}, {2, 1}
};
int BFS(){
    typedef tuple<int, int, int> tp;
    queue<tp> Q;
    vector<vector<bool>> visited(I, vector<bool>(I, false));
    Q.push({0,cur.first, cur.second});
    visited[cur.first][cur.second] = true;

    while(!Q.empty()){
        int y, x, cnt;
        tie(cnt,y, x) = Q.front(); Q.pop();
        if(y == target.first && x == target.second){
            return cnt;
        }
        for(int i=0; i<8; i++){
            int ny = y + dir[i][0], nx = x + dir[i][1];
            if(ny < 0 || nx < 0 || ny >= I || nx >= I || visited[ny][nx])
                continue;
            visited[ny][nx] = true;
            Q.push({cnt+1, ny, nx});
        }
    }
    return -1;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int T;
    cin >> T;
    while(T--){
        cin >> I;
        cin >> cur.first >> cur.second;
        cin >> target.first >> target.second;
        int ans = BFS();
        cout << ans << '\n';
    }

    return 0;
}
