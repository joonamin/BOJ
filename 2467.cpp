#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N;
    cin >> N;

    vector<long long> arr(N);
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }
    sort(arr.begin(), arr.end());

    int left = 0, right = N-1;
    pair<int,int> answer = {left, right};
    while(left < right){
        long long sum = arr[left] + arr[right];
        if( abs(sum) < abs(arr[answer.first] + arr[answer.second]) )
            answer = {left, right};

        if( sum < 0 ){
            left += 1;
        }
        else
            right -= 1;

    }
    cout << arr[answer.first] << ' ' << arr[answer.second];

    return 0;
}
