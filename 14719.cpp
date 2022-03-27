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
    int H, W;
    cin >> H >> W;
    vector<int> arr(W);
    for(auto &item : arr)
        cin >> item;

    int ans = 0;
    for(int i=1; i<W-1; i++){
        int lmx = i-1, rmx = i+1;
        for(int j=i-1; j>=0; j--){
            if(arr[lmx] < arr[j])
                lmx = j;
        }
        for(int j=i+1; j<W; j++){
            if(arr[rmx] < arr[j])
                rmx = j;
        }
        ans += max(0, min(arr[lmx], arr[rmx]) - arr[i]);
    }
    cout << ans ;
    return 0;
}
