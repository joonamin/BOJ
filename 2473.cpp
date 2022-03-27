#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N;
    cin >> N;

    vector<long long> arr(N);
    for(int i=0; i<N; i++)
        cin >> arr[i];

    sort(arr.begin(), arr.end());

    tuple<int,int,int> answer = {0, (N-1)/2, N-1};
    for(int i=0; i<N; i++){
        // 하나를 고정시켜두자
        long long fixed = arr[i];

        int left = 0, right = N-1;
        while(left < right){
            if( i == left || i == right )
                break;
            if( abs(arr[left] + arr[right] + fixed) < abs(arr[get<0>(answer)] + arr[get<1>(answer)] + arr[get<2>(answer)]) )
                answer = {left, i, right};
            // arr[left] + arr[right] ~ -fixed 와 최대한 가깝게 다가가야함
            if(arr[left]+arr[right] < -fixed){
                left += 1;
            }
            else
                right -= 1;
        }

    }
    cout << arr[get<0>(answer)] << ' ' << arr[get<1>(answer)] << ' ' << arr[get<2>(answer)];

    return 0;
}
