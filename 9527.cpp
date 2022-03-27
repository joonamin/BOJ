#include <bits/stdc++.h>
typedef long long ll;
using namespace std;
ll dp[56];
ll prefix_sum(ll n){
    ll ret = n & 1 ? 1 : 0;
    for(int i = 55; i > 0 ; i--){
        if( n & (1LL << i) ){
            // i번째 비트가 켜졌을 경우
            ret += dp[i-1] + ( n - (1LL<<i) + 1 );
            n -= (1LL << i );
        }
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    /*  x   |   bin(x)    | f(x)                ∑f(x)
     *  1       1          1                    1
     *  ======================
     *  2       10         1                    2
     *  3       11         2    => 1 + 1        4
     *  ======================
     *  4       100        1                    5
     *  5       101        2                    7
     *  6       110        2    => 1 + 1        9
     *  7       111        3    => 2 + 1        12
     *  ======================
     *  8       1000       1                    13
     *  9       1001       2                    15
     *  10      1010       2                    17
     *  11      1011       3                    20
     *  12      1100       2    => 1 + 1        22
     *  13      1101       3    => 2 + 1        25
     *  14      1110       3    => 2 + 1        28
     *  15      1111       4    => 3 + 1        32
     *  ======================
     *  이러한 패턴이 나타나는 이유 : 자리 수 마다 이전 것이 반복되는 패턴
     *  dp[i] : 1111..1(최상위 비트:i) 이하의 수 중 ∑f(i)의 값
     *  dp[0] = 1
     *  dp[1] = 2*dp[0] + 2*1
     *  dp[2] = 2*dp[1] + 2^2
     *  dp[k] = 2*dp[k-1] + 2^k
     */
    dp[0] =1 ;
    for(int i=1; i<=55; i++) {
        dp[i] = dp[i - 1] * 2 + (1LL << i);
    }
    ll A, B;
    cin >> A >> B;
    cout << prefix_sum(B) - prefix_sum(A-1);

    return 0;
}