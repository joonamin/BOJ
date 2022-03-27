#include <bits/stdc++.h>
using namespace std;
vector<long long> ac_sum;
long long get_sum(int left, int right){
    // left <= right
    if( left == 0 )
        return ac_sum[right];
    return ac_sum[right] - ac_sum[left-1];
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    long long N, M;
    cin >> N >> M;

    ac_sum.resize(N, 0);

    vector<int> arr(N);
    long long temp = 0;
    for(int i=0; i<N; i++){
        cin >> arr[i];
        temp += arr[i];
        ac_sum[i] = temp;
    }

    // with two-pointer
    int left = 0, right = 0;
    int answer = 0;
    while( 0 <= left && left < N && 0 <= right && right < N ){
        long long sum = get_sum(left, right);
        if( sum == M ){
            answer += 1;
            left += 1, right += 1;
        }
        else if( sum < M ){
            // sum 이 더 커져야 하는 상황
            right += 1;
        }
        else{
            // sum 이 더 작아져야 하는 상황
            left += 1;
        }
    }

    cout << answer << '\n';


    return 0;
}
