#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, minDist[25][25];
bool isValid[25][25] = {false, };
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin >> minDist[i][j];
        }
    }
    memset(isValid, true, sizeof(isValid));
    for(int k=0; k<N; k++){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(minDist[i][j] == minDist[i][k] + minDist[k][j] && k != i && k != j)
                    isValid[i][j] = false;
                else if(minDist[i][j] > minDist[i][k] + minDist[k][j]){
                    cout << -1 << '\n';
                    exit(0);
                }
            }
        }
    }

    int ans = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            if(isValid[i][j])
                ans += minDist[i][j];
    }
    cout << ans / 2;
    return 0;
}
