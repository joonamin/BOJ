#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int N;
    cin >> N;

    vector<int> arr(N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }

    // dp[i] : i까지 검사했을 때 부분 증가 수열의 길이
    vector<int> dp(N, 1);
    // 현재까지 밟고 온 것들의 경로
    vector<int> backtrace[N];
    for(int i=0; i<N; i++){
        backtrace[i].push_back(arr[i]);
    }

    for(int i=1; i<N; i++){
        for(int j=0; j<i; j++){
            if( arr[j] < arr[i] && dp[i] < dp[j] + 1 ){
                dp[i] = dp[j] + 1;
                backtrace[i] = backtrace[j];
                backtrace[i].push_back(arr[i]);
            }
        }
    }
    // dp[0 ~ N-1] 중 가장 큰 값이 가장 긴 증가하는 부분 수열의 길이가 됨
    int max_idx = 0;
    for(int i=1; i<N; i++){
        if( dp[max_idx] < dp[i] ){
            max_idx = i;
        }
    }
    cout << dp[max_idx] << '\n';
    for(auto elem : backtrace[max_idx])
        cout << elem << ' ';
    return 0;
}