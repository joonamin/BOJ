#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N, M;
    cin >> N;

    vector<int> arr(N);
    for(int i=0; i<N; i++)
        cin >> arr[i];
    sort(arr.begin(), arr.end());

    cin >> M;
    for(int i=0; i<M; i++){
        int target; cin >> target;
        if(binary_search(arr.begin(), arr.end(), target))
            cout << 1 << ' ';
        else
            cout << 0 << ' ';
    }


    return 0;
}