#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;


int n;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> n;
    vector<int> arr(n), dp;
    for(int i=0; i<n; i++)
        cin >> arr[i];

    for(int i=0; i<n; i++){
        if(dp.empty() || dp.back() < arr[i])
            dp.push_back(arr[i]);
        else{
            int idx = lower_bound(dp.begin(), dp.end(), arr[i]) - dp.begin();
            dp[idx] = arr[i];
        }
    }
    cout << dp.size() << '\n';


    return 0;
}
