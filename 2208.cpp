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
    int arr[100005], N, M, loss[100005] = {0, }, prefix[100005] = {0,};
    cin >> N >> M;
    int sum = 0;
    for(int i=0; i<N; i++){
        cin >> arr[i];
        sum += arr[i];
        prefix[i] = sum;
    }

    loss[0] = 0;
    for(int i=1; i<N; i++){
        loss[i] = min(loss[i-1], prefix[i-1]);
    }
    int ans = 0;
    for(int i=M-1; i<N; i++){
        ans = max(ans, prefix[i] - loss[i-M+1]);
    }
    cout << ans;

    return 0;
}
