#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
string word[3];
int dp[201][201];
int solve(int i, int j, int k){
    int &ret = dp[j][k];
    if(ret != -1)
        return ret;
    if(i == word[2].size()){
        if(j == word[0].size() && k == word[1].size())
            return ret = 1;
        return ret = 0;
    }
    ret = 0;
    // 현재 i번째 word[3]까지 word[0] 가 j번째, word[1]이 k번째 까지 매칭되었을 때
    // 해당 단어를 끝까지 완성할 수 있는가?
    if(word[2][i] == word[0][j])
        ret |= solve(i+1, j+1, k);
    if(!ret && word[2][i] == word[1][k])
        ret |= solve(i+1, j, k+1);
    return ret;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int T;
    cin >> T;
    for(int i=1; i<=T; i++){
        memset(dp, -1, sizeof(dp));

        cin >> word[0] >> word[1] >> word[2];
        solve(0, 0, 0);
        cout << "Data set " << i << ": ";
        if(dp[word[0].size()][word[1].size()] == -1)
            cout << "no\n";
        else
            cout << "yes\n";
    }
    return 0;
}
