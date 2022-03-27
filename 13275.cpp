#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    string S;
    cin >> S;

    vector<int> odd_dp(S.size(), 1), even_dp(S.size(), 1);
    int L = 0, R = -1;
    // odd_dp[i] : i를 중심으로 하는 가장 긴 팰린드롬의 길이가 x일 때, ( x/2 + 1 ) 만큼 저장
    for(int i=0; i<S.size(); i++){
        int len = ( R < i ) ? 1 : min(odd_dp[L+R-i], R-i+1);
        while( 0 <= i - len && i + len < S.size() && S[i-len] == S[i+len] )
            len += 1;

        odd_dp[i] = len--;
        if( i + len > R  ){
            L = i - len;
            R = i + len;
        }
    }

    L = 0 , R = -1;
    for(int i=0; i<S.size(); i++){
        // p1 = ceil( (L+R)/2 ) , p2 = ceil( (L+R)/ 2 ) + 1
        int len = ( R < i )? 0 : min(odd_dp[L+R-i+1], R-i+1);
        while( 0 <= i - len - 1 && i + len < S.size() && S[i-len-1] == S[i+len] )
            len += 1;
        even_dp[i] = len--;
        if( i + len > R ){
            L = i - 1 - len;
            R = i + len ;
        }
    }

    int ans = 0;
    for(auto it : odd_dp){
        ans = max(ans, (it-1)*2 + 1);
    }
    for(auto it : even_dp){
        ans = max(ans, 2*it);
    }
    cout << ans;
    return 0;
}