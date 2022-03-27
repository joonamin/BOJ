#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N;
    cin >> N;

    vector<int> arr(N);
    for(int i=0; i<N; i++)
        cin >> arr[i];

    sort(arr.begin(), arr.end());

    int left = 0, right = N-1;


    pair<int,int> answer ={left, right};
    while( left < right ){
        long long sum = arr[left] + arr[right];
        if( abs(sum) < abs(arr[answer.first] + arr[answer.second]) ){
            answer.first = left ;
            answer.second = right ;
        }
        if( sum > 0 ){
            right -= 1;
        }
        else
            left += 1;
    }
    cout << arr[answer.first] << ' ' << arr[answer.second];


    return 0;
}
