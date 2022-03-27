#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N;
vector<int> arr, prefix;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N;
    arr.resize(N+1, 0);
    prefix.resize(N+1, 0);

    int sum = 0;
    for(int i=1; i<=N; i++){
        cin >> arr[i];
        sum += arr[i];
        prefix[i] = sum;
    }

    int k;
    cin >> k;
    while(k--){
        int l, r;
        cin >> l >> r;
        cout << prefix[r] - prefix[l-1] << '\n';
    }


    return 0;
}
