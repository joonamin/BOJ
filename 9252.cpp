#include <bits/stdc++.h>
using namespace std;
string a, b;
void trace_back(const int (*dp)[1001], string& lcs, int i, int j ){
    if( i == 0 || j == 0 )
        return ;
    if( a[i] == b[j] ){
        lcs = a[i] + lcs;
        trace_back(dp, lcs, i-1, j-1);
    }
    else if( dp[i-1][j] > dp[i][j-1] )
        trace_back(dp,lcs, i-1, j);
    else
        trace_back(dp, lcs, i, j-1);
    return ;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    cin >> a >> b;
    a = '0' + a;  b = '0' + b;
    int a_size = a.size()-1, b_size = b.size()-1;


    int dp[1001][1001] ;
    dp[0][0] = 0;
    for(int i=1; i<=a.size(); i++)
        dp[i][0] = 0;
    for(int i=1; i<=b.size(); i++)
        dp[0][i] = 0;

    for(int i=1; i<=a_size; i++){
        for(int j=1; j<=b_size; j++){
            if( a[i] == b[j] ){
                dp[i][j] = dp[i-1][j-1] + 1;
            }
            else
                dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
        }
    }


    string lcs = "" ;
    trace_back(dp, lcs, a_size, b_size);

    cout << dp[a_size][b_size] << '\n';
    if( lcs != "" )
        cout << lcs;

    return 0;
}