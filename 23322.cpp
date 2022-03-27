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

    int N, K;
    cin >> N >> K;
    vector<int> arr(N);
    for(auto &item : arr)
        cin >> item;

    int ans = 0, time = 0;
    for(int i=1; i<N; i++){
        if(arr[i] != arr[0]){
            ans += arr[i] - arr[0];
            time += 1;
        }
    }
    cout << ans << ' ' << time << '\n';
    return 0;
}
