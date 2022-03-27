#include <iostream>
#include <vector>

using namespace std;

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    int N;
    cin >> N;
    vector<int> arr(N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }
    vector<int> dp(N);
    // Longest increasing subsequence
    for(int i=0; i<N; i++){
        dp[i] = 1;
        for(int j=0; j<i; j++){
            if( arr[j] < arr[i] && dp[j] + 1 > dp[i] )
                dp[i] = dp[j] + 1;
        }
    }

    int unAligned_max = 0;
    for( auto it : dp ){
        if( it > unAligned_max ){
            unAligned_max = it;
        }
    }
    cout << N - unAligned_max ;

    return 0;
}