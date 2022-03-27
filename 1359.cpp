#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
double comb[10][10] = {0, };

int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    comb[0][0] = 1;
    for(int i=1; i<10; i++){
        for(int j=0; j<=i; j++){
            if( i-1 >= 0 )
                comb[i][j] += comb[i-1][j-1];
            comb[i][j] += comb[i-1][j];
        }
    }
    int N, M, K;
    cin >> N >> M >> K;
    double ans = 0.0;
    for(int i=K; i<=M; i++){
        if( N-M < M-i ) continue;
        ans += (comb[M][i] * comb[N-M][M-i]);
    }
    ans /= comb[N][M];
    cout.precision(15);
    cout << fixed;
    cout << ans ;

    return 0;
}
