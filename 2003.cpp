#include <bits/stdc++.h>
using namespace std;

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N,M;
    cin >> N >> M;
    vector<int> arr(N);
    for(int i=0; i<N; i++)
        cin >> arr[i];

    int answer = 0;
    // two pointers
    for(int s=0; s<N; s++){
        int sum = arr[s];
        if( sum == M ) {
            answer += 1;
            continue;
        }

        int e = s + 1;
        while( e < N ){
            if( sum + arr[e] == M ){
                answer += 1;
                break;
            }
            else if( sum + arr[e] < M ){
                sum += arr[e];
                e++;
            }
            else{
                break;
            }
        }
    }
    cout << answer;

    return 0;
}