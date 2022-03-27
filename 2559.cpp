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
    int N, K, sum = 0;
    cin >> N >> K;
    int prefix[100005] = {0, }, tem[100005];
    for(int i=0; i<N; i++){
        cin >> tem[i];
        sum += tem[i];
        prefix[i] = sum;
    }
    int ans = -2e9;
    for(int i=0; i<=N-K; i++){
        // [i, i+k-1]
        int left = (i == 0) ? 0 : prefix[i-1];
        ans = max(ans, prefix[i+K-1] - left);
    }
    cout << ans;

    return 0;
}
