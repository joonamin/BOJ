#include <bits/stdc++.h>
using namespace std;
int N;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N ;

    vector<int> r(N), c(N);

    for(int i=0; i<N; i++){
        cin >> r[i] >> c[i];
    }

    // 하나의 덩어리는 크기가 더 작은 덩어리들로 분할이 가능하다.
    int dp[501][501] = {0, };
    // base AB, BC 등 2개의 행렬이 묶일 때
    for(int i=0; i<N-1; i++){
        dp[i][i+1] = r[i] * r[i+1] * c[i+1];
    }

    // [left, right] 의 인덱스 차이 = gap
    for(int gap = 2; gap < N ; gap++){
        for(int left=0; left < N-gap; left++){
            int right = left + gap;
            // [left ~ right] 의 연산 횟수의 최솟값
            for(int m = left ; m < right ; m++){
                // [left, m] 의 크기 : r[left] x c[m]
                // [m+1, right] 의 크기 : r[m+1] x c[right]
                // 문제 조건에 따라, c[m] == r[m+1]
                int res = dp[left][m] + dp[m+1][right] + r[left] * c[m] * c[right];
                if( dp[left][right] == 0 || dp[left][right] > res )
                    dp[left][right] = res;
            }
        }
    }
    cout << dp[0][N-1];
    return 0;
}