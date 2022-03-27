#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N, K;
    cin >> N >> K;

    vector<int> pos(N);
    for(int i=0; i<N; i++){
        cin >> pos[i];
    }

    sort(pos.begin(), pos.end());

    int answer = 0;
    for(int i=0; i<N; ){
        int coverage = pos[i] + K - 1;
        int j = i+1;
        while( j < N && pos[j] <= coverage ) j+=1;
        i = j;
        answer += 1;
    }
    cout << answer;
    return 0;
}
