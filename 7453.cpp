#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int n;
    cin >> n;

    vector<long long> arr[4];

    for(int i=0; i<n; i++){
        for(int j=0; j<4; j++){
            long long e ;
            cin >> e;
            arr[j].push_back(e);
        }
    }

    for(int i=0; i<4; i++)
        sort(arr[i].begin(), arr[i].end());

    vector<long long> sum ;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            sum.push_back(arr[2][i] + arr[3][j]);
        }
    }
    sort(sum.begin(), sum.end());

    long long answer = 0 ;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            long long s = arr[0][i] + arr[1][j];
            int lo = lower_bound(sum.begin(), sum.end(), -s) - sum.begin();
            int hi = upper_bound(sum.begin(), sum.end(), -s) - sum.begin();

            if( -s == sum[lo])
                answer += hi - lo;
        }
    }
    cout << answer ;



    return 0;
}
