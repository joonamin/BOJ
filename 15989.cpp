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

    int T;
    cin >> T;
    while(T--){
        int n, ans = 0;
        cin >> n;

        for(int i=0; i * 2 <= n; i++){
            int m = n - 2 * i;
            for(int j=0; j * 3 <= m; j++){
                ans += 1;
            }
        }
        cout << ans << '\n';
    }


    return 0;
}
