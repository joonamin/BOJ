#include <bits/stdc++.h>
using namespace std;
long long T;
int N, M;
int A[1001], B[1001];
vector<long long> acc_A, acc_B;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> T;
    cin >> N ;

    long long sum = 0;
    for(int i=0; i<N; i++){
        cin >> A[i];
        sum += A[i];
        acc_A.push_back(sum);
    }

    sum = 0;
    cin >> M;
    for(int i=0; i<M; i++){
        cin >> B[i];
        sum += B[i];
        acc_B.push_back(sum);
    }

    vector<long long> sum_of_subarr_B;
    for(int i=0; i<M; i++){
        for(int j=i; j<M; j++){
            long long elem = acc_B[j];
            if( i - 1 >= 0 )
                elem -= acc_B[i-1];
            sum_of_subarr_B.push_back(elem);
        }
    }
    sort(sum_of_subarr_B.begin(), sum_of_subarr_B.end());
    // A의 pair 를 Fix
    long long ans = 0;
    for(int i=0; i<N; i++){
        for(int j=i; j<N; j++){
            long long s = acc_A[j];
            if( i - 1 >= 0 )
                s -= acc_A[i-1];
            long long target = T - s;
            int st = lower_bound(sum_of_subarr_B.begin(), sum_of_subarr_B.end(), target) - sum_of_subarr_B.begin();
            int end = upper_bound(sum_of_subarr_B.begin(), sum_of_subarr_B.end(), target) - sum_of_subarr_B.begin();
            ans += (end - st);
        }
    }

    cout << ans ;
    return 0;
}