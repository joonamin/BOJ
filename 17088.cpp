#include <bits/stdc++.h>
using namespace std;
int N;
vector<int> B;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    B.resize(N);
    for(int i=0; i<N; i++){
        cin >> B[i];
    }
    int ans = 1e9;

    for(int i=-1; i<=1; i++){
        for(int j=-1; j<=1; j++){
            int f = B[0] + i;
            int s = B[1] + j;
            const int diff = s - f;
            int cnt = 0;
            cnt += ( i == 0 ) ? 0 : 1;
            cnt += ( j == 0 ) ? 0 : 1;
            // 인접한 2개의 항만 살펴보면 된다.
            bool canGo = true;
            for(int k=2; k<N; k++){
                f = s;
                s = B[k];
                if( s - f + 1 == diff ){
                    cnt += 1;
                    s += 1;
                }
                else if( s - f - 1 == diff ){
                    cnt += 1;
                    s -= 1;
                }
                else if( s - f == diff ){
                    continue;
                }
                else{
                    canGo = false;
                    break;
                }
            }
            if( canGo ){
                ans = min(ans, cnt);
            }
        }
    }
    if( ans == 1e9 )
        cout << -1 ;
    else
        cout << ans ;
    return 0;
}
