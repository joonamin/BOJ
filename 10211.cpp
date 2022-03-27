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
        int N;
        cin >> N;

        vector<int> v(N);
        for(int i=0; i<N; i++)
            cin >> v[i];

        // 현재까지의 누적합
        int prefix = v[0], ans = v[0];
        for(int i=1; i<N; i++){
            prefix = max(prefix + v[i], v[i]);
            ans = max(prefix, ans);
        }
        cout << ans << '\n';
    }


    return 0;
}
