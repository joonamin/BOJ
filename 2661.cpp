#include <bits/stdc++.h>
using namespace std;
int N;
string ans ;
bool check(string s){
    // 현재 문자열이 좋은 수열인가?
    int len = s.length();
    if( len == 1 ) return true;

    // 길이는 최대 len
    for(int l = 1; l <= len; l++){
        for(int j = 0; j<len; j++) {
            string prev = "";
            for (int i = j; i <= len - l; i += l) {
                string sub = s.substr(i, l);
                if (prev != "" && prev == sub)
                    return false;
                prev = sub;
            }
        }
    }
    return true;
}
void solve(int rest, string cur){
    if( rest == 0 ){
        if( ans == "" || ans > cur )
            ans = cur;
        cout << ans;
        exit(0);
        return ;
    }
    for(char c = '1'; c <= '3'; c++){
        string next = cur + c;
        if( check(next) ){
            solve(rest-1, next);
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    cin >> N;
    solve(N, "");
    return 0;
}