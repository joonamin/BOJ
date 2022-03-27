#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int M, N;
    cin >> M >> N;
    pair<int,int> arr[101][10001];
    for(int i=0; i<M; i++){
        for(int j=0; j<N; j++) {
            cin >> arr[i][j].first;
            arr[i][j].second = j;
        }
    }
    for(int i=0; i<M; i++)
        sort(arr[i], arr[i]+N);
    for(int i=0; i<M; i++){
        for(int j=0; j<N-1; j++){
            if( arr[i][j].first == arr[i][j+1].first )
                arr[i][j+1].second = arr[i][j].second;
        }
    }
    int ans = 0;

    for(int i=0; i<M; i++){
        for(int j=i+1; j<M; j++){
            bool canMatch = true;
            for(int k=0; k<N; k++){
                if( arr[i][k].second != arr[j][k].second )
                    canMatch = false;
            }
            if( canMatch ) ans += 1;
        }
    }
    cout << ans ;
    return 0;
}