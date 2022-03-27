#include <bits/stdc++.h>
using namespace std;
int n;
int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);
    string S;
    cin >> S;
    n = S.size();

    vector<int> odd_dp(n), even_dp(n);

    int l = 0, r = -1;
    // 홀수 크기의 팰린드롬에 대하여 고려
    for(int i=0; i<n; i++){
        int sz = ( i > r ) ? 1 : min(r-i+1, odd_dp[l+r-i]);
        while( i - sz >= 0 && i + sz < n && S[i-sz] == S[i+sz] )
            sz += 1;
        odd_dp[i] = sz--;
        if( i + sz > r ){
            l = i - sz;
            r = i + sz;
        }
    }
    l = 0, r = -1;
    // 짝수 크기의 팰린드롬에 대하여 고려
    for(int i=0; i<n; i++){
        int sz = ( i > r ) ? 0 : min(r-i+1, even_dp[l+r-i-1]);
        while( i - sz - 1 >= 0 && i + sz < n && S[i-sz-1] == S[i+sz] )
            sz += 1;

        even_dp[i] = sz--;
        if( i + sz > r ){
            l = i - sz - 1;
            r = i + sz;
        }
    }

    int answer = 0;
    for(int i=0; i<n; i++){
        int odd_len = odd_dp[i] * 2 - 1;
        int even_len = even_dp[i] * 2;
        int val = max(odd_len, even_len);
        answer = max(val, answer);
    }
    cout << answer ;

    return 0;
}