#include <bits/stdc++.h>
using namespace std;
string max( string a, string b ){
    if( a.size() == b.size() ){
        if( a < b )
            return b;
        return a;
    }
    if( a.size() < b.size() )
        return b;
    return a;
}
string make_str(string src, string n){
    string ret;
    for(int i=0; i<src.size(); i++){
        string tmp = src;
        tmp.insert(i, n);
        ret = max(ret, tmp);
    }
    // 앞의 leading-zero 처리
    int idx = 0;
    // test
    if( ret != "0" && ret[0] == '0' ){
        while( idx < ret.size() && ret[idx] == '0' )
            idx += 1;
        ret = ret.substr(idx, ret.size()-idx);
    }

    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N;
    cin >> N ;

    vector<int> point(N);
    for(int i=0 ; i<N; i++){
        cin >> point[i];
    }

    int K;
    cin >> K;
    // k금액으로 만들 수 있는 가장 큰 수(문자열)
    string dp[51];
    // base
    for(int i=0; i<N; i++){
        for(int j=point[i]; j<=50; j++){
            string val = to_string(i);
            dp[j] = max( dp[j], val );
        }
    }

    for(int i=1; i<=50; i++){
        for(int j=0; j<N; j++){
            string new_str = to_string(j);
            int pt = point[j];
            if( i - pt >= 0 ){
                dp[i] = max(dp[i], make_str(dp[i-pt], new_str) );
            }
        }
    }

    cout << dp[K];

    return 0;
}