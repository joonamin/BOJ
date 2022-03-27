#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N;
    cin >> N ;

    vector<int> arr(N);
    for(int i=0; i<N; i++)
        cin >> arr[i];

    vector<int> dp; // dp[i] : i+1 길이를 가지는 LIS중 마지막 원소의 최솟값
    dp.push_back(arr[0]);
    for(int i=1; i<N; i++){
        if( dp.back() < arr[i] )
            dp.push_back(arr[i]);
        else{
            int largerThan = lower_bound(dp.begin(), dp.end(), arr[i]) - dp.begin();
            dp[largerThan] = arr[i];
        }
    }
    cout << dp.size();

    return 0;
}
