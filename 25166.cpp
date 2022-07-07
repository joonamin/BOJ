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
    int S, M;
    cin >> S >> M;

    if (S > 1023) {
        if (S - 1023 - M > 0 || (((S - 1023) & M) != S - 1023)) {
            cout << "Impossible";
        } else {
            cout << "Thanks";
        }
    } else {
        cout << "No thanks";
    }
    return 0;
}
