#include <iostream>
#include <vector>

using namespace std;
vector<int> DP;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N;
    cin >> N;
    vector<int> arr(N);
    DP.resize(N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }

    // DP[i] 를 i를 마지막으로 포함하는 증가부분수열중 합이 가장 큰 것.
    for(int i=0; i<N; i++){
        DP[i] = arr[i]; // 항상 자기 자신을 부분수열로 가짐.
        for(int j=0; j<i; j++){
            if( arr[j] < arr[i] && DP[j] + arr[i] > DP[i] )
                DP[i] = DP[j] + arr[i];
        }
    }

    int max = -1;
    for( auto it : DP ){
        if( it > max ){
            max = it;
        }
    }
    cout << max;

    return 0;
}