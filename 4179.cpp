#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
char board[1005][1005];
const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
bool personVisited[1005][1005] = {false, };
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int R, C;
    cin >> R >> C;

    queue<pair<int,int>> fireQ;
    queue<tuple<int,int,int>> personQ;

    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            cin >> board[i][j];
            if(board[i][j] == 'F')
                fireQ.push({i, j});
            if(board[i][j] == 'J')
                personQ.push({0, i, j});
        }
    }

    int fSize = fireQ.size(), pSize = personQ.size(), ans = 1e9;
    while(pSize > 0) {
        for(int i=0; i<fSize; i++){
            auto [y, x] = fireQ.front(); fireQ.pop();
            for(int d=0; d<4; d++){
                int ny = y + dir[d][0], nx = x + dir[d][1];
                if(ny < 0 || nx < 0 || ny >= R || nx >= C)
                    continue;
                if(board[ny][nx] == '#' || board[ny][nx] == 'F')
                    continue;
                board[ny][nx] = 'F';
                fireQ.push({ny, nx});
            }
        }

        for(int i=0; i<pSize; i++){
            auto [time, y, x] = personQ.front(); personQ.pop();
            if(y == 0 || y == R-1 || x == 0 || x == C-1){
                ans = min(ans, time + 1);
                continue;
            }
            for(int d=0; d<4; d++){
                int ny = y + dir[d][0], nx = x + dir[d][1];
                if(ny < 0 || nx < 0 || ny >= R || nx >= C)
                    continue;
                if(board[ny][nx] == 'F' || board[ny][nx] == '#')
                    continue;
                if(personVisited[ny][nx])
                    continue;
                personVisited[ny][nx] = true;
                personQ.push({time+1, ny, nx});
            }
        }
        fSize = fireQ.size();
        pSize = personQ.size();
    }
    if(ans == 1e9)
        cout << "IMPOSSIBLE\n";
    else
        cout << ans << '\n';
    return 0;
}
