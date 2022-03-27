#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;


typedef long long ll;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    map<ll, bool> mp;
    int N;
    cin >> N;
    ll sum = 0;

    vector<ll> arr(N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
        sum += arr[i];
    }

    set<ll> dp[21];
    dp[0].insert(arr[0]);
    for(int i=1; i<N; i++){
        const set<ll>& prev = dp[i-1];
        dp[i].insert(arr[i]);
        for(const auto& item : prev) {
            dp[i].insert(item + arr[i]);
            dp[i].insert(item);
        }
    }

    cout << sum - (ll)dp[N-1].size();


    return 0;
}
