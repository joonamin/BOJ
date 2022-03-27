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

    vector<int> lis(N), lds(N);
    vector<int> bitonic(N);

    for(int i=0; i<N; i++){
        lis[i] = 1;
        for(int j=0; j<i; j++){
            if( arr[j] < arr[i] && lis[j]+1 > lis[i] ){
                lis[i] = lis[j]+1;
            }
        }
    }
    for(int i=N-1; i>=0; i--){
        lds[i] = 1;
        for(int j= i+1; j < N ; j++){
            if( arr[i] > arr[j] && lds[i] < lds[j]+1 ){
                lds[i] = lds[j] + 1;
            }
        }
    }

    for(int i=0; i<N; i++){
        // 자기 자신이 중복됨. (-1)
        bitonic[i] = lis[i] + lds[i] - 1;

    }

    int max_len  = -1;
    for(auto it : bitonic){
        if( max_len < it )
            max_len = it;
    }
    cout << max_len;

    return 0;
}