#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, M;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N >> M;
    int ac, D;
    cin >> ac >> D;

    const int dir[2][2] = {{0, -1}, {0, 1}};

    int sr, sc;
    cin >> sr >> sc;

    int y = 1, x = ac, d = D;

    bool f = false;
    while (y <= N && (y != N || x != M)) {
        if (y == sr && x == sc) {
            f = true;
            break;
        }
        if ((x == 1 && d == 0) || (x == M && d == 1)) {
            y += 1;
            d = (d + 1) % 2;
        } else {
            x += dir[d][1];
        }
    }
    if(!f) cout << "YES!";
    else cout << "NO...";

    return 0;
}
