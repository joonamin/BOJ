#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll calc(string S){
    // K(Q) := Q라는 문자열의 길이 * K
    // K(Q + k(Q)) 꼴 생각
    int i = 0, j = S.size() - 1;
    while(i < S.size() && S[i] != '(') i++;
    while(j >= 0 && S[j] != ')') j--;

    if(i == S.size() || j == -1)
        return S.size();

    ll K = S[i-1] - '0';
    string Q = S.substr(i+1, j-i-1);
    string left = S.substr(0, i-1);
    // Q := K(Q) 의 집합의 연속으로 표현 가능
    // ex : Q = K(Q)K(Q)K(Q)...
    vector<string> sub;
    i = 0;
    while(i < Q.size()){
        j = i + 1;
        int init = true, br = 0;
        while(j < Q.size() && (init || br > 0)){
            if(Q[j] == '('){
                br += 1;
                init = false;
            }
            else if(Q[j] == ')'){
                br -= 1;
                init = false;
            }
            j += 1;
        }
        sub.push_back(Q.substr(i, j-i));
        i = j;
    }
    ll tot = 0 ;
    for(auto s : sub){
         tot +=  calc(s);
    }
    return K * tot + left.size();
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
    //////////////////////////////////////////////////////////////////
    string s;
    cin >> s;

    int i = 0;
    ll ans = 0;
    while(i < s.size()){
        // () 가 완전히 pop될 때 까지 파싱
        int j = i + 1;
        int init = true, br = 0;
        while(j < s.size() && (init || br > 0)){
            if(s[j] == '(') {
                br += 1;
                init = false;
            }
            else if(s[j] == ')'){
                br -= 1;
                init = false;
            }
            j += 1;
        }
        ans += calc(s.substr(i, j-i));
        i = j;
    }
    cout << ans << '\n';
    return 0;
}
