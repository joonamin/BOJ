#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int N;
vector<ll> arr, dp;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N;
    arr.resize(N);
    for(int i=0; i<N; i++)
        cin >> arr[i];

    for(int i=0; i<N; i++){
        if(dp.empty() || dp.back() < arr[i]){
            dp.push_back(arr[i]);
        }else{
            int idx = lower_bound(dp.begin(), dp.end(), arr[i]) - dp.begin();
            dp[idx] = arr[i];
        }
    }
    cout << dp.size();


    return 0;
}
