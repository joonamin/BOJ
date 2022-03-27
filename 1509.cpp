#include <bits/stdc++.h>
using namespace std;
bool isPalind[2501][2501] = {false, };
int dp[2501][2501] = {0, };
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    // 전처리 하는 과정 필요

    // isPalind[i][j] : [i,j] 까지의 문자열이 팰린드롬인가 ?
    // 분할 시, dp[i] : i번째 항까지 고려하였을 때 분할의 최솟 값
    string str;
    cin >> str;

    int size = str.size();

    for(int i=0; i<size; i++){
        isPalind[i][i] = true;
    }
    for(int i=0; i<size-1; i++){
        if( str[i] == str[i+1] )
            isPalind[i][i+1] = true;
    }

    for(int sz = 3; sz <= size; sz++){
        for(int i=0; i<=size-sz; i++){
            int j = i + sz - 1;
            if( str[i] == str[j] && isPalind[i+1][j-1] )
                isPalind[i][j] = true;
        }
    }

    int dp[2501]; // [0, i]까지 분할의 최소값
    dp[0] = 1;
    for(int i=1; i<size; i++){
        dp[i] = dp[i-1] + 1;
        if( isPalind[0][i] )
            dp[i] = 1;
        for(int j=0; j<i; j++){
            if( j + 1 <= i && isPalind[j+1][i] )
                dp[i] = min(dp[i], dp[j] + 1 );
        }
    }
    cout << dp[size-1];
    return 0;
}
