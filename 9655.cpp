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

    // 돌은 1개 또는 3개만 가져갈 수 있음
    // N개의 돌이 있다고 가정하였울 때, 돌의 개수는 0개로 수렴한다.
    int N;
    cin >> N;

    if (N % 2 == 0) {
        cout << "CY";
    } else {
        cout << "SK";
    }

    return 0;
}
