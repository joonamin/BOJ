#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;


int W, N;
int dp[799995], pos[200001];
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> W >> N;
    memset(dp, -1, sizeof(dp));
    memset(pos, -1, sizeof(pos));

    vector<int> arr(N);
    for(int i=0; i<N; i++) {
        cin >> arr[i];
        pos[arr[i]] = i;
    }

    for(int i=0; i<N; i++){
        for(int j=i+1; j<N; j++){
            int sum = arr[i] + arr[j];
            if( sum > W ) continue;
            dp[sum] = i;
        }
    }
    for(int i=0; i<N; i++){
        for(int j=i+1; j<N; j++){
            int sum = arr[i] + arr[j];
            if( W - sum < 0 ) continue;
            if( dp[W-sum] == i || dp[W-sum] == j ) continue;
            if( pos[W-sum-arr[dp[W-sum]]] == i || pos[W-sum-arr[dp[W-sum]]] == j ) continue;
            if( dp[W-sum] != -1 ) {
                cout << "YES\n";
                return 0;
            }
        }
    }
    cout << "NO\n";
    return 0;
}
