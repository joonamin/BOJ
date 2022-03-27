#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    int N, M;
    cin >> N >> M ;

    bool v[201][201] = {false, };

    for(int i=0; i<M; i++){
        int a, b;
        cin >> a >> b;
        v[a][b] = v[b][a] = true;
    }
    int ans = 0;
    for(int i=1; i<=N; i++){
        for(int j=i+1; j<=N; j++){
            for(int k=j+1; k<=N; k++){
                if( !v[i][j] && !v[j][k] && !v[i][k] )
                    ans += 1;
            }
        }
    }
    cout << ans ;
    return 0;
}
