#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    int F, S, G, U, D;
    cin >> F >> S >> G >> U >> D;

    bool visited[1000005] = {false, };
    queue<pair<int,int>> Q;
    Q.push({S, 0});

    while(!Q.empty()) {
        auto [cur, cnt] = Q.front(); Q.pop();
        if(cur == G) {
            cout << cnt << '\n';
            return 0;
        }
        if(cur + U <= F && !visited[cur + U]) {
            Q.push({cur + U, cnt + 1});
            visited[cur + U] = true;
        }
        if(cur - D >= 1 && !visited[cur - D]) {
            Q.push({cur - D, cnt + 1});
            visited[cur - D] = true;
        }
    }
    cout << "use the stairs\n";
    return 0;
}
