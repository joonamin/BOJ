#include <bits/stdc++.h>
using namespace std;
string a,b,c;
int dp[101][101][101];
int max(int a, int b, int c){
    int ret = a;
    ret = ( ret < b ) ? b : ret;
    ret = ( ret < c ) ? c : ret;
    return ret;
}
void init(){
    for(int i=0; i<b.size(); i++){
        for(int j=0; j<c.size(); j++)
            dp[0][i][j] = 0;
    }
    for(int i=0; i<a.size(); i++){
        for(int j=0; j<c.size(); j++)
            dp[i][0][j] = 0;
    }
    for(int i=0; i<a.size(); i++){
        for(int j=0; j<c.size(); j++)
            dp[i][j][0] = 0;
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> a >> b >> c;
    a = '\0' + a, b = '\0' + b, c = '\0' + c;

    init();

    for(int i=1; i<a.size(); i++){
        for(int j=1; j<b.size(); j++){
            for(int k=1; k<c.size(); k++){
                if( a[i] == b[j] && b[j] == c[k] && a[i] == c[k] )
                    dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                else{
                    int ret1 = max(dp[i][j-1][k-1], dp[i][j-1][k], dp[i][j][k-1] );
                    int ret2 = max(dp[i-1][j][k-1], dp[i-1][j][k], dp[i][j][k-1] );
                    int ret3 = max(dp[i-1][j-1][k], dp[i-1][j][k], dp[i][j-1][k] );
                    dp[i][j][k] = max(ret1,ret2,ret3);
                }
            }
        }
    }

    cout << dp[a.size()-1][b.size()-1][c.size()-1];


    return 0;
}