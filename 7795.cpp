#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int T;
    cin >> T;

    while(T--){
        int N, M;
        cin >> N >> M;
        vector<int> v1(N), v2(M);
        for(int i=0; i<N; i++)
            cin >> v1[i];
        for(int i=0; i<M; i++)
            cin >> v2[i];
        sort(v2.begin(), v2.end());
        int ans = 0;
        for(int i=0; i<N; i++){
            ans += lower_bound(v2.begin(), v2.end(), v1[i]) - v2.begin();
        }
        cout << ans << '\n';
    }

    return 0;
}